package com.example.hoover.service;

import com.example.hoover.entity.HooverEntity;
import com.example.hoover.mapper.Mapper;
import com.example.hoover.repository.HooverRepository;
import com.example.hoover.request.HooverRequest;
import com.example.hoover.response.HooverResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HooverRepositoryService {
    @Autowired
    HooverRepository hooverRepository;

    public void save(HooverRequest hooverRequest, HooverResponse hooverResponse) throws JsonProcessingException {
        String hooverRequestString  =  Mapper.mapRequestResponseToJsonString(hooverRequest);
        String hooverRepsonseString  =  Mapper.mapRequestResponseToJsonString(hooverResponse);

        HooverEntity hooverEntity = new HooverEntity.Builder().
                setRequest(hooverRequestString)
                .setResponse(hooverRepsonseString)
                .build();
        hooverRepository.save(hooverEntity);
    }
}
