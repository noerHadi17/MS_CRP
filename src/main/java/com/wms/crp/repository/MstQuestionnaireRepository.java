package com.wms.crp.repository;

import com.wms.crp.entity.MstQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MstQuestionnaireRepository extends JpaRepository<MstQuestionnaire, UUID> {
    List<MstQuestionnaire> findAllByIsActiveTrueOrderBySeqAsc();
}

