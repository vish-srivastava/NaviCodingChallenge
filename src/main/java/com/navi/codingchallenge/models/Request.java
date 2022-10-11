package com.navi.codingchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Request {
    private InputType inputType;
    private String emiNumber;
    private String[] inputParams;

}
