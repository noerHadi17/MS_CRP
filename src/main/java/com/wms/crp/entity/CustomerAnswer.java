package com.wms.crp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = EntityNames.CUSTOMER_ANSWERS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAnswer {
    @Id
    @Column(name = EntityNames.CustomerAnswer.CUSTOMER_ANSWER_ID)
    private UUID customerAnswerId;

    @Column(name = EntityNames.CustomerAnswer.ID_CUSTOMER)
    private UUID idCustomer;

    @Column(name = EntityNames.CustomerAnswer.ID_QUESTION)
    private UUID idQuestion;

    @Column(name = EntityNames.CustomerAnswer.ID_ANSWER)
    private UUID idAnswer;
}
