package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LumpSumPaymentRequest {

    private String bankName;
    private String borrowerName;
    private Double lumpSum;
    private Integer emisPaid;

}
