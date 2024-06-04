package com.capgemini.wsb.fitnesstracker.training.api.dto;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor()
@AllArgsConstructor
public class TrainingDto { private Long id; private Long userId; private UserEntity user; @JsonDeserialize(using = CalculateDate.class) private Date startTime; @JsonDeserialize(using = CalculateDate.class)  private Date endTime; private ActivityType activityType; private double distance; private double averageSpeed;
}

//@Getter
//@Setter
//public record TrainingDto(@Nullable Long Id, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
//}