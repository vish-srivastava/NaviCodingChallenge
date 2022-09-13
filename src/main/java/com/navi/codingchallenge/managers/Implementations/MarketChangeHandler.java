package com.navi.codingchallenge.managers.Implementations;

import com.navi.codingchallenge.MarketEventListener;
import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;

import java.time.Month;

public class MarketChangeHandler implements MarketEventListener {

    @Override
    public Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception {
        if (eventType == EventType.CHANGE) {
            Month currentMonth = Month.valueOf(inputParams[inputParams.length - 1]);
            updatePortfolioForMonth(portfolio, inputParams);
            portfolio.setCurrentMonth(currentMonth);
        }
        return portfolio;
    }

    private Portfolio updatePortfolioForMonth(Portfolio portfolio, String[] inputParams) {
        Double equityChange, debtChange, goldChange;
        equityChange = Double.parseDouble(inputParams[1].replace("%", "")) / 100.00;
        debtChange = Double.parseDouble(inputParams[2].replace("%", "")) / 100.00;
        goldChange = Double.parseDouble(inputParams[3].replace("%", "")) / 100.00;
        portfolio.updatePortfolioForMarketChange(equityChange, debtChange, goldChange);
        portfolio.incrementSipCount();
        return portfolio;
    }
}
