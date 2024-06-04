package com.capgemini.wsb.fitnesstracker;

import com.capgemini.wsb.fitnesstracker.training.api.entity.TrainingEntity;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    @Autowired
    private JpaRepository<UserEntity, Long> userRepository;

    @Autowired
    private JpaRepository<TrainingEntity, Long> trainingRepository;

    @AfterEach
    void cleanUpDB() {
        trainingRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Before
    public void setUp() {
        trainingRepository.deleteAll();
        userRepository.deleteAll();

    }

    protected TrainingEntity persistTraining(TrainingEntity training) {
        return trainingRepository.save(training);
    }

    protected UserEntity existingUser(UserEntity user) {

        return userRepository.save(user);
    }

    protected List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    protected List<TrainingEntity> createAllTrainings(List<TrainingEntity> trainings) {

        trainings.forEach(training -> trainingRepository.save(training));
        return trainings;
    }

    protected List<TrainingEntity> getAllTrainings() {
        return trainingRepository.findAll();
    }


}
