package com.wms.crp.repository;

import com.wms.crp.entity.CustomerAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAnswerRepository extends JpaRepository<CustomerAnswer, UUID> { }

