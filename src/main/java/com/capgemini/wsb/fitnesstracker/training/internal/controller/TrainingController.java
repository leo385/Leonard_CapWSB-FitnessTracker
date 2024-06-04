package com.capgemini.wsb.fitnesstracker.training.internal.controller;


import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.mapper.TrainingMapper;
import com.capgemini.wsb.fitnesstracker.training.internal.impl.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;


    private final TrainingMapper trainingMapper;

    /*
        Get trainings by user id
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {

        return trainingService.findTrainingsByUserId(userId).stream().map(trainingMapper::toDto).toList();

    }

    /*
        Get trainings from database.
     */
    @GetMapping
    public List<TrainingDto> getListOfTrainings() {
        return trainingService.findAllTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {

        /*
            Get trainings by activity
         */
        return trainingService.findTrainingsByActivity(activityType).stream().map(trainingMapper::toDto).toList();
    }

    /*
        Get trainings that have been completed.
     */
    @GetMapping("/done/{time}")
    public List<TrainingDto> getTrainingsCompleted(@PathVariable
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {

        return trainingService.findTrainingsAreCompleted(time).stream().map(trainingMapper::toDto).toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /*
        Add trainings to database.
     */
    public TrainingDto createTrainingInDatabase(@RequestBody TrainingDto trainingDto) {

        TrainingEntity training = trainingMapper.toEntity(trainingDto);

        TrainingEntity createdTraining = trainingService.createTrainingInDatabase(training);

        return trainingMapper.toDto(createdTraining);
    }


    /*
        update trainings in database.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTrainingInDatabase(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {

        return trainingMapper.toDto(trainingService.updateTrainingInDatabase(id, trainingDto));
    }

}
