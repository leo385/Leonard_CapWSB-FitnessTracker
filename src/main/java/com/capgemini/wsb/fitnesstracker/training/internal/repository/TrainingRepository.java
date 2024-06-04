package com.capgemini.wsb.fitnesstracker.training.internal.repository;

import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

}
