package com.wms.crp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = EntityNames.MST_RISKPROFILES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstRiskProfile {
    @Id
    @Column(name = EntityNames.MstRiskProfile.RISK_PROFILE_ID)
    private UUID riskProfileId;

    @Column(name = EntityNames.MstRiskProfile.PROFILE_TYPE)
    private String profileType;

    @Column(name = EntityNames.MstRiskProfile.SCORE_MIN)
    private Integer scoreMin;

    @Column(name = EntityNames.MstRiskProfile.SCORE_MAX)
    private Integer scoreMax;
}
