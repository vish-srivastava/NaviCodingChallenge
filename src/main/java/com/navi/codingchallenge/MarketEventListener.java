package com.navi.codingchallenge;

import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;

public interface MarketEventListener {
    Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception;
}
