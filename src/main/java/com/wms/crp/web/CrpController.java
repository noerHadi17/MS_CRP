package com.wms.crp.web;

import com.wms.crp.dto.CrpDtos.*;
import com.wms.crp.dto.request.SubmitAnswersRequest;
import com.wms.crp.service.AnswerService;
import com.wms.crp.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import com.wms.crp.i18n.I18nMessageCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/v1/crp")
@RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
public class CrpController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MessageSource messageSource;

    @GetMapping("/questions")
    public ResponseEntity<ResponseWrapper<QuestionListResponse>> questions(Locale locale) {
        log.info("[crp] questions getActive");
        QuestionListResponse res = questionService.getActive();
        String msg = I18nMessageCollection.QUESTION_LIST_RETRIEVED.localized(messageSource, locale);
        return ResponseEntity.ok(ApiResponseUtil.success(res, I18nMessageCollection.QUESTION_LIST_RETRIEVED.name(), msg));

    }

    @PostMapping("/answers/validate")
    public ResponseEntity<ResponseWrapper<ScoreResponse>> validate(@RequestHeader("X-User-Id") UUID userId, @RequestBody @Valid SubmitAnswersRequest req, Locale locale) {
        log.info("[crp] validate userId={} answers={} ", userId, req.getAnswers().size());
        ScoreResponse res = answerService.validate(userId, req);
        String msg = I18nMessageCollection.QUESTIONNAIRE_PREVIEW_SUCCESS.localized(messageSource, locale);
        return ResponseEntity.ok(ApiResponseUtil.success(res, I18nMessageCollection.QUESTIONNAIRE_PREVIEW_SUCCESS.name(), msg));
    }

    @PostMapping("/answers/save")
    public ResponseEntity<ResponseWrapper<SaveResponse>> save(@RequestHeader("X-User-Id") UUID userId, @RequestBody @Valid SubmitAnswersRequest req, Locale locale) {
        log.info("[crp] save userId={} answers={}", userId, req.getAnswers().size());
        SaveResponse res = answerService.save(userId, req);
        String msg = I18nMessageCollection.QUESTIONNAIRE_SUBMIT_SUCCESS.localized(messageSource, locale);
        return ResponseEntity.ok(ApiResponseUtil.success(res, I18nMessageCollection.QUESTIONNAIRE_SUBMIT_SUCCESS.name(), msg));
    }
}
