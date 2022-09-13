package com.navi.codingchallenge.managers.Implementations;

import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;
import com.navi.codingchallenge.MarketEventListener;

public class SIPManager implements MarketEventListener {
    @Override
    public Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception {
        if (eventType == EventType.SIP) {
            return addSIPtoPortfolio(portfolio, inputParams);
        } else {
            return updateMonthlyBalance(portfolio);
        }

    }

    private Portfolio addSIPtoPortfolio(Portfolio portfolio, String[] inputParams) {
        Double equityAmount = Double.parseDouble((inputParams[1]));
        Double debtAmount = Double.parseDouble((inputParams[2]));
        Double goldAmount = Double.parseDouble((inputParams[3]));

        portfolio.getEquity().setMonthlySipAmount(equityAmount);
        portfolio.getDebt().setMonthlySipAmount(debtAmount);
        portfolio.getGold().setMonthlySipAmount(goldAmount);

        return portfolio;
    }

    private Portfolio updateMonthlyBalance(Portfolio portfolio) {
        if (portfolio.getSipCount() >= 1) {
            portfolio.updateMonthlyBalanceAfterSIP();
        }
        return portfolio;
    }
}
