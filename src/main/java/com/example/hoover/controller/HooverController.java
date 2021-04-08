package com.example.hoover.controller;

import com.example.hoover.request.HooverRequest;
import com.example.hoover.response.HooverResponse;
import com.example.hoover.service.HooverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HooverController {
    @Autowired
    HooverService hooverService ;

    @GetMapping(value = "/getPatchesCleanedNumber")
    public ResponseEntity<HooverResponse> getCleanedSpots(@Validated @RequestBody HooverRequest hooverRequest) throws Exception{
        return ResponseEntity.ok(hooverService.countCleanedSpots(hooverRequest));
    }
}
