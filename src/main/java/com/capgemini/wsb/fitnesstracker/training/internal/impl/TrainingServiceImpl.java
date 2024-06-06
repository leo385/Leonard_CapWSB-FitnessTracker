package com.capgemini.wsb.fitnesstracker.training.internal.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;

import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.exceptions.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.service.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.repository.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TrainingServiceImpl implements TrainingProvider {


    private final TrainingRepository trainingRepository;

    @Override
    public TrainingEntity createTrainingInDatabase(TrainingEntity training) {

        return trainingRepository.save(training);

    }

    @Override
    public TrainingEntity updateTrainingInDatabase(Long id, TrainingDto trainingDto) {

        TrainingEntity training = trainingRepository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));

        training.setStartTime(trainingDto.getStartTime());
        training.setEndTime(trainingDto.getEndTime());

        training.setActivityType(trainingDto.getActivityType());

        training.setDistance(trainingDto.getDistance());
        training.setAverageSpeed(trainingDto.getAverageSpeed());

        return trainingRepository.save(training);
    }


    @Override
    public void deleteTrainingFromDatabaseByUserId(Long userId) {

        Optional<TrainingEntity> first = trainingRepository.findAll().stream().filter(training -> training.getUser().getId().equals(userId)).findFirst();

        first.ifPresent(trainingRepository::delete);
    }

    @Override
    public List<TrainingEntity> findAllTrainings() {

        return trainingRepository.findAll();

    }

    @Override
    public List<TrainingEntity> findTrainingsByActivity(ActivityType activityType) {

        return trainingRepository.findAll().stream().filter(training -> training.getActivityType() == activityType).toList();

    }

    @Override
    public List<TrainingEntity> findTrainingsByUserId(Long userId) {

        return trainingRepository.findAll().stream().filter(training -> training.getUser().getId().equals(userId)).toList();

    }

    @Override
    public List<TrainingEntity> findTrainingsAreCompleted(LocalDate date) {

        return trainingRepository.findAll().stream().filter(training -> training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(date)).toList();

    }


    @Override
    public Optional<UserEntity> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }


}
