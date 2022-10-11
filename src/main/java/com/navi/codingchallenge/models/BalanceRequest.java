package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BalanceRequest {
    private String bankName;
    private String borrower;
    private Integer emiNumber;

}
