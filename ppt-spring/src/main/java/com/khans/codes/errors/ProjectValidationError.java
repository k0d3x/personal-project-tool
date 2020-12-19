package com.khans.codes.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ProjectValidationError {
    /*
     * list of error map;
     */
    Map<String,String> errorMessages;
}
