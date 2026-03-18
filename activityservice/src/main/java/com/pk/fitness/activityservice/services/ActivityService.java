package com.pk.fitness.activityservice.services;

import com.pk.fitness.activityservice.dtos.ActivityRequest;
import com.pk.fitness.activityservice.dtos.ActivityResponse;

public interface ActivityService {

    ActivityResponse trackActivity(ActivityRequest request);
    ActivityResponse getActivity(String id);
}
