package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;

import java.util.Optional;

// TODO: Provide Impl
public class TrainingServiceImpl implements TrainingProvider {

    @Override
    public Optional<UserEntity> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

}
