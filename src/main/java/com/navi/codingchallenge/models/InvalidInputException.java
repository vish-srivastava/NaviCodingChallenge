package com.navi.codingchallenge.models;

import lombok.AllArgsConstructor;

public class InvalidInputException extends Exception {
    public InvalidInputException(String s) {
        super(s);
    }
}
