package com.navi.codingchallenge.managers.Implementations;

import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;
import com.navi.codingchallenge.models.Distribution;
import com.navi.codingchallenge.MarketEventListener;

import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PortfolioRebalancingManager implements MarketEventListener {

    private static String REBALANCING_ERROR_MESSAGE = "CANNOT_REBALANCE";

    private static Set<Month> validRebalancingMonths = new HashSet<>(Arrays.asList(
            Month.valueOf("JUNE"), Month.valueOf("DECEMBER")
    ));

    @Override
    public Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception {
        return rebalance(portfolio);
    }

    private Portfolio rebalance(Portfolio portfolio) {

        Distribution equity = portfolio.getEquity();
        Distribution debt = portfolio.getDebt();
        Distribution gold = portfolio.getGold();

        Month currentMonth = portfolio.getCurrentMonth();

        if (!validRebalancingMonths.contains(currentMonth)) {
            System.out.println(REBALANCING_ERROR_MESSAGE);
            portfolio.setRebalanced(false);
            return portfolio;
        }

        Double totalValue = equity.getAmount() + debt.getAmount() + gold.getAmount();
        Double newEquityAmount = equity.getPercentage() * totalValue;
        Double newDebtAmount = debt.getPercentage() * totalValue;
        Double newGoldAmount = gold.getPercentage() * totalValue;
        portfolio.setRebalanced(true);
        portfolio.getEquity().setAmount(newEquityAmount);
        portfolio.getDebt().setAmount(newDebtAmount);
        portfolio.getGold().setAmount(newGoldAmount);

        return portfolio;
    }
}
