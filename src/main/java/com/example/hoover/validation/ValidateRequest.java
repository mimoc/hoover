package com.example.hoover.validation;

import com.example.hoover.request.HooverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequest {

    @Autowired
    @Qualifier("syntaxValidation")
    Validate syntaxValidator;

    @Autowired
    @Qualifier("semanticValidation")
    Validate semanticValidator;

    public void validate(HooverRequest hooverRequest){
        syntaxValidator.validate(hooverRequest);
        semanticValidator.validate(hooverRequest);
    }
}
