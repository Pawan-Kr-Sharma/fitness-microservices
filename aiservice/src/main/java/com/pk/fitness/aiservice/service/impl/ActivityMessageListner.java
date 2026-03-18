package com.pk.fitness.aiservice.service.impl;

import com.pk.fitness.aiservice.entities.Activity;
import com.pk.fitness.aiservice.entities.Recommendation;
import com.pk.fitness.aiservice.repositories.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListner {

    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    /*@Value("${kafka.topic.name}")
    private String topicName;*/

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        log.info("Received Activity for processing: {}", activity.getUserId());

        Recommendation recommendation = activityAiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
        log.info("Ai recommendation saved successfully !!");
    }

}
