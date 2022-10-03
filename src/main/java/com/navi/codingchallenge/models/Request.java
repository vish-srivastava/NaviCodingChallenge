package com.navi.codingchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Request {
    InputType inputType;
    String emiNumber;
    String [] inputParams;

}
