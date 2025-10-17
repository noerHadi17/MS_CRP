package com.wms.crp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = EntityNames.MST_ANSWERS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstAnswer {
    @Id
    @Column(name = EntityNames.MstAnswer.ANSWER_ID)
    private UUID answerId;

    @Column(name = EntityNames.MstAnswer.ID_QUESTION)
    private UUID idQuestion;

    @Column(name = EntityNames.MstAnswer.ANSWER_TEXT)
    private String answerText;

    @Column(name = EntityNames.MstAnswer.SCORE_VALUE)
    private Integer scoreValue;

    @Column(name = EntityNames.MstAnswer.SEQ)
    private Integer seq;
}
