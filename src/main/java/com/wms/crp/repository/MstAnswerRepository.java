package com.wms.crp.repository;

import com.wms.crp.entity.MstAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface MstAnswerRepository extends JpaRepository<MstAnswer, UUID> {
    List<MstAnswer> findAllByIdQuestionIn(Collection<UUID> ids);
}

