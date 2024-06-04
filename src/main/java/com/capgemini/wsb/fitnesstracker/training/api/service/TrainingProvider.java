package com.capgemini.wsb.fitnesstracker.training.api.service;

import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {


    TrainingEntity createTrainingInDatabase(TrainingEntity training);
    TrainingEntity updateTrainingInDatabase(Long id, TrainingDto trainingDto);

    void deleteTrainingFromDatabaseByUserId(Long userId);

    List<TrainingEntity> findAllTrainings();
    List<TrainingEntity> findTrainingsByActivity(ActivityType activityType);

    List<TrainingEntity> findTrainingsByUserId(Long userId);
    List<TrainingEntity> findTrainingsAreCompleted(LocalDate date);

    Optional<UserEntity> getTraining(Long trainingId);


}
