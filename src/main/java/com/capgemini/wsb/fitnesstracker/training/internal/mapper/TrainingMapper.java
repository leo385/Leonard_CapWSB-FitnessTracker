package com.capgemini.wsb.fitnesstracker.training.internal.mapper;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import com.capgemini.wsb.fitnesstracker.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;


@RequiredArgsConstructor
@Component
public class TrainingMapper {


    private final UserRepository userRepository;

    public TrainingEntity toEntity(TrainingDto trainingDto) {


        UserEntity user = userRepository.findById(trainingDto.getUserId()).orElseThrow();


        return new TrainingEntity(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());

    }


    public TrainingDto toDto(TrainingEntity training) {

        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());

    }



}
