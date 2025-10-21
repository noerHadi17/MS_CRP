package com.wms.crp.service;

import com.wms.crp.dto.CrpDtos.*;
import com.wms.crp.dto.request.SubmitAnswersRequest;
import com.wms.crp.entity.CustomerAnswer;
import com.wms.crp.kafka.AuditEventProducer;
import com.wms.crp.repository.CustomerAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final ScoringService scoringService;
    private final CustomerAnswerRepository answerRepository;
    private final AuditEventProducer auditEventProducer;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${services.customer.base-url:http://localhost:8080}")
    private String customerBaseUrl;

    public ScoreResponse validate(UUID customerId, SubmitAnswersRequest req) {
        return scoringService.calculate(req.getAnswers());
    }

    public SaveResponse save(UUID customerId, SubmitAnswersRequest req) {

        List<CustomerAnswer> rows = req.getAnswers().stream()
                .map(a -> CustomerAnswer.builder()
                        .customerAnswerId(UUID.randomUUID())
                        .idCustomer(customerId)
                        .idQuestion(a.getQuestionId())
                        .idAnswer(a.getAnswerId())
                        .build()).toList();
        answerRepository.saveAll(rows);

        ScoreResponse score = scoringService.calculate(req.getAnswers());

        // Produce audit event
        auditEventProducer.sendAuditEvent("CRP_RESULT_SAVED", String.valueOf(customerId), null, "SUCCESS", "CRP results saved");

        // Notify customer-service about updated risk profile via REST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = customerBaseUrl + "/v1/user/risk-profile";
        String payload = String.format("{\"customerId\":\"%s\",\"riskProfileId\":\"%s\"}", customerId, score.getRiskProfile().getRiskProfileId());
        boolean updated = false;
        try {
            var resp = restTemplate.postForEntity(url, new HttpEntity<>(payload, headers), String.class);
            updated = resp.getStatusCode().is2xxSuccessful();
            if (updated) {
                log.info("Updated customer risk profile via REST for customerId={} rp={}", customerId, score.getRiskProfile().getRiskProfileId());
            }
        } catch (Exception ex) {
            log.warn("Failed REST update risk profile: {}", ex.getMessage());
        }
        // If REST notify fails, we log and continue without cross-service DB writes



        return SaveResponse.builder()
                .customerId(customerId)
                .totalScore(score.getTotalScore())
                .riskProfile(score.getRiskProfile())
                .build();
    }
}
