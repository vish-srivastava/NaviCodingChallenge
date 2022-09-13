package com.navi.codingchallenge.managers.Implementations;

import com.navi.codingchallenge.models.Portfolio;
import com.navi.codingchallenge.MarketEventListener;
import com.navi.codingchallenge.models.EventType;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PortfolioAuditManager implements MarketEventListener {

    private static Map<Month, LinkedList<Integer>> audits = new HashMap<>();

    @Override
    public Portfolio handleEvent(EventType eventType, Portfolio portfolio, String[] inputParams) throws Exception {
        String month = inputParams[inputParams.length - 1];

        if (eventType == EventType.BALANCE) {
            getPortfolioForMonth(month);
        } else {
            if (eventType == EventType.REBALANCE) {
                if (portfolio.isRebalanced()) {
                    month = portfolio.getCurrentMonth().minus(1).name();
                    addPortfolioAuditForMonth(portfolio, month);
                    getPortfolioForMonth(month);
                }
            } else {
                addPortfolioAuditForMonth(portfolio, month);
            }
        }
        return portfolio;
    }

    private void addPortfolioAuditForMonth(Portfolio portfolio, String month) {
        LinkedList<Integer> balanceForMonth = new LinkedList<>();
        balanceForMonth.add(portfolio.getEquity().getAmount().intValue());
        balanceForMonth.add(portfolio.getDebt().getAmount().intValue());
        balanceForMonth.add(portfolio.getGold().getAmount().intValue());
        Month currentMonth = Month.valueOf(month);
        audits.put(currentMonth, balanceForMonth);
    }

    private void getPortfolioForMonth(String month) throws Exception {
        Month currentMonth = Month.valueOf(month);

        LinkedList<Integer> balanceForMonth = audits.get(currentMonth);
        if (balanceForMonth == null || balanceForMonth.isEmpty()) {
            throw new Exception("Portfolio balance not found for month" + month);
        }

        System.out.println(balanceForMonth.get(0) + " " + balanceForMonth.get(1) + " " + balanceForMonth.get(2));
    }
}
