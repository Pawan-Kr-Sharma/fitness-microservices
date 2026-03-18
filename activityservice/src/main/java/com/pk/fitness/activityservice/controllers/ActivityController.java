package com.pk.fitness.activityservice.controllers;

import com.pk.fitness.activityservice.dtos.ActivityRequest;
import com.pk.fitness.activityservice.dtos.ActivityResponse;
import com.pk.fitness.activityservice.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.trackActivity(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getUserActivity(@PathVariable String id) {
        return ResponseEntity.ok(activityService.getActivity(id));
    }
}
