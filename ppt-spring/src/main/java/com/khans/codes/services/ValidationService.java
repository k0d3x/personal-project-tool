package com.khans.codes.services;

import com.khans.codes.errors.ProjectValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ValidationService {

    public Optional<ProjectValidationError> validate(BindingResult result){
        if(result.hasErrors()){
            log.error("Validation Failed!");
            ProjectValidationError error = new ProjectValidationError();
            Map<String,String> errorMessageMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                errorMessageMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            error.setErrorMessages(errorMessageMap);
            return Optional.of(error);
        }
        else
            return Optional.empty();
    }
}
