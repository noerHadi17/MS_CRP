package com.wms.crp.service;

import com.wms.crp.dto.request.QA;
import com.wms.crp.dto.CrpDtos.RiskProfile;
import com.wms.crp.dto.CrpDtos.ScoreResponse;
import com.wms.crp.i18n.RiskI18n;
import com.wms.crp.entity.MstAnswer;
import com.wms.crp.entity.MstRiskProfile;
import com.wms.crp.exception.BusinessException;
import com.wms.crp.repository.MstAnswerRepository;
import com.wms.crp.repository.MstRiskProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoringService {
    private final MstAnswerRepository answerRepository;
    private final MstRiskProfileRepository riskProfileRepository;
    private final MessageSource messageSource;

    public ScoreResponse calculate(List<QA> answers) {
        if (answers == null || answers.isEmpty()) throw new BusinessException("QUESTIONNAIRE_INCOMPLETE");
        Map<UUID, UUID> qToAnswer = answers.stream().collect(Collectors.toMap(QA::getQuestionId, QA::getAnswerId, (a,b) -> a));
        List<MstAnswer> existing = answerRepository.findAllById(qToAnswer.values());
        if (existing.size() != qToAnswer.size()) throw new BusinessException("ANSWER_INVALID");
        int total = 0;
        for (MstAnswer a : existing) {
            UUID expectedQ = a.getIdQuestion();
            if (!Objects.equals(qToAnswer.get(expectedQ), a.getAnswerId())) throw new BusinessException("ANSWER_INVALID");
            total += Optional.ofNullable(a.getScoreValue()).orElse(0);
        }
        MstRiskProfile rp = riskProfileRepository.findByScoreMinLessThanEqualAndScoreMaxGreaterThanEqual(total, total)
                .orElseThrow(() -> new BusinessException("ANSWER_INVALID"));
        String profileName = rp.getProfileType();
        RiskI18n type = RiskI18n.fromProfileType(profileName);
        String insight = messageSource.getMessage(type.key(), null, LocaleContextHolder.getLocale());
        return ScoreResponse.builder().totalScore(total)
                .riskProfile(RiskProfile.builder().riskProfileId(rp.getRiskProfileId()).riskProfileName(profileName).build())
                .insight(insight)
                .build();
    }
}
