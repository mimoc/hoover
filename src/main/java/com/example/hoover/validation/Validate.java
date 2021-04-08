package com.example.hoover.validation;

import com.example.hoover.request.HooverRequest;

import java.util.ArrayList;
import java.util.List;

public interface Validate {
    List<String> validationErrorList = new ArrayList<>();
    default void addError(String errrorMessage){
        validationErrorList.add(errrorMessage);
    }
    default void emptyErrorList(){
        validationErrorList.removeAll(validationErrorList);
    }
    void validate(HooverRequest hooverRequest);
}
