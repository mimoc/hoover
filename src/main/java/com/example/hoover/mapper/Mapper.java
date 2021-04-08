package com.example.hoover.mapper;

import com.example.hoover.interfaces.Hoover;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    public static String mapRequestResponseToJsonString(Hoover hoover) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsString(hoover);
    }
}