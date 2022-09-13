package com.navi.codingchallenge.managers.Implementations;

import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;
import com.navi.codingchallenge.models.Distribution;
import com.navi.codingchallenge.MarketEventListener;

public class PortfolioAllocationManager implements MarketEventListener {

    @Override
    public Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception {
        if (eventType == EventType.ALLOCATE) {
            if (portfolio != null) {
                throw new Exception("Portfolio has already been Allocated");
            }
            portfolio = Portfolio.getInstance();
            allocateFundsToPortfolio(portfolio, inputParams);
        }
        return portfolio;
    }

    private Portfolio allocateFundsToPortfolio(Portfolio portfolio, String[] inputParams) throws Exception {

        Double equityAmount = Double.parseDouble((inputParams[1]));
        Double debtAmount = Double.parseDouble((inputParams[2]));
        Double goldAmount = Double.parseDouble((inputParams[3]));

        Double total = equityAmount + goldAmount + debtAmount;

        Double equityPercentage = equityAmount / total;
        Double debtPercentage = debtAmount / total;
        Double goldPercentage = goldAmount / total;

        Distribution equityDistribution = new Distribution(equityAmount, equityPercentage);
        Distribution debtDistribution = new Distribution(debtAmount, debtPercentage);
        Distribution goldDistribution = new Distribution(goldAmount, goldPercentage);

        portfolio.setEquity(equityDistribution);
        portfolio.setDebt(debtDistribution);
        portfolio.setGold(goldDistribution);

        return portfolio;
    }
}
