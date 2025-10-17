package com.wms.crp.repository;

import com.wms.crp.entity.MstRiskProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MstRiskProfileRepository extends JpaRepository<MstRiskProfile, UUID> {
    Optional<MstRiskProfile> findByScoreMinLessThanEqualAndScoreMaxGreaterThanEqual(Integer min, Integer max);
}

