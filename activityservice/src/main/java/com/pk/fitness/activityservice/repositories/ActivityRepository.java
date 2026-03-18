package com.pk.fitness.activityservice.repositories;

import com.pk.fitness.activityservice.entities.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
}
