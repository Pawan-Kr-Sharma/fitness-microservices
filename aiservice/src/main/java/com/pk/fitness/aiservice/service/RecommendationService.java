package com.pk.fitness.aiservice.service;

import com.pk.fitness.aiservice.entities.Recommendation;

import java.util.List;

public interface RecommendationService {

    List<Recommendation> getUserRecommendation(String userId);
    Recommendation getActivityRecommendation(String activityId);
}
