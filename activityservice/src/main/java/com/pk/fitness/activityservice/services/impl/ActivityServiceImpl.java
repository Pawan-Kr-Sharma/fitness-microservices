package com.pk.fitness.activityservice.services.impl;

import com.pk.fitness.activityservice.dtos.ActivityRequest;
import com.pk.fitness.activityservice.dtos.ActivityResponse;
import com.pk.fitness.activityservice.entities.Activity;
import com.pk.fitness.activityservice.repositories.ActivityRepository;
import com.pk.fitness.activityservice.services.ActivityService;
import com.pk.fitness.activityservice.services.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        System.out.println(isValidUser);

        if (!isValidUser) throw new RuntimeException("Invalid User: " + request.getUserId());

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);

        try {
            kafkaTemplate.send(topicName,savedActivity.getUserId(),savedActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* return ActivityResponse.builder()
                .id(savedActitvity.getId())
                .userId(savedActitvity.getUserId())
                .type(savedActitvity.getType())
                .duration(savedActitvity.getDuration())
                .caloriesBurned(savedActitvity.getCaloriesBurned())
                .startTime(savedActitvity.getStartTime())
                .additionalMetrics(savedActitvity.getAdditionalMetrics())
                .createdAt(savedActitvity.getCreatedAt())
                .updatedAt(savedActitvity.getUpdatedAt())
                .build();*/
        return mapToActivityResponse(savedActivity);
    }

    @Override
    public ActivityResponse getActivity(String id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new RuntimeException("Activity not found with given id"));
        return mapToActivityResponse(activity);
    }

    private ActivityResponse mapToActivityResponse(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUserId())
                .type(activity.getType())
                .duration(activity.getDuration())
                .caloriesBurned(activity.getCaloriesBurned())
                .startTime(activity.getStartTime())
                .additionalMetrics(activity.getAdditionalMetrics())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }
}
