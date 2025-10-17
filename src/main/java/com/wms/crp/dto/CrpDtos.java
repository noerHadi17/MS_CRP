package com.wms.crp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

public class CrpDtos {
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class QuestionItem { private UUID questionId; private String questionText; private Integer seq; private List<AnswerItem> answers; }
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AnswerItem { private UUID answerId; private String answerText; private Integer seq; }
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class QuestionListResponse { private List<QuestionItem> items; }


    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ScoreResponse { private int totalScore; private RiskProfile riskProfile; private String insight; }
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class RiskProfile { private UUID riskProfileId; private String riskProfileName; }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SaveResponse { private UUID customerId; private UUID questionnaireId; private int totalScore; private RiskProfile riskProfile; }
}
