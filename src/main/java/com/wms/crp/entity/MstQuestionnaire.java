package com.wms.crp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = EntityNames.MST_QUESTIONNAIRES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstQuestionnaire {
    @Id
    @Column(name = EntityNames.MstQuestionnaire.QUESTION_ID)
    private UUID questionId;

    @Column(name = EntityNames.MstQuestionnaire.QUESTION_TEXT)
    private String questionText;

    @Column(name = EntityNames.MstQuestionnaire.SEQ)
    private Integer seq;

    @Column(name = EntityNames.MstQuestionnaire.IS_ACTIVE)
    private Boolean isActive;
}
