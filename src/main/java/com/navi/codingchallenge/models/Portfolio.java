package com.navi.codingchallenge.models;

import java.time.LocalDate;
import java.time.Month;

public class Portfolio implements Cloneable {
    private static Portfolio portfolio;

    Distribution equity;
    Distribution debt;
    Distribution gold;

    Boolean rebalanced;

    Month currentMonth;

    public Integer getSipCount() {
        return sipCount;
    }

    public void incrementSipCount() {
        this.sipCount++;
    }

    Integer sipCount = 0;


    synchronized public static Portfolio getInstance() {
        if (portfolio == null) {
            portfolio = new Portfolio();
        }
        return portfolio;
    }


    public Month getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(Month currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Distribution getEquity() {
        return equity;
    }

    public void setEquity(Distribution equity) {
        this.equity = equity;
    }

    public Distribution getDebt() {
        return debt;
    }

    public void setDebt(Distribution debt) {
        this.debt = debt;
    }

    public Distribution getGold() {
        return gold;
    }

    public void setGold(Distribution gold) {
        this.gold = gold;
    }

    public void setRebalanced(Boolean rebalanced) {
        this.rebalanced = rebalanced;
    }

    public Boolean isRebalanced() {
        return this.rebalanced;
    }

    public void updateMonthlyBalanceAfterSIP() {
        this.equity.amount += this.getEquity().monthlySipAmount;
        this.debt.amount += this.getDebt().monthlySipAmount;
        this.gold.amount += this.getGold().monthlySipAmount;
        floorToNearestInteger();
    }

    public void updatePortfolioForMarketChange(Double equityChange, Double debtChange, Double goldChange) {
        this.equity.amount += this.equity.amount * equityChange;
        this.debt.amount += this.debt.amount * debtChange;
        this.gold.amount += this.gold.amount * goldChange;

        floorToNearestInteger();
    }

    private void floorToNearestInteger() {
        this.equity.amount = (double) this.equity.amount.intValue();
        this.debt.amount = (double) this.debt.amount.intValue();
        this.gold.amount = (double) this.gold.amount.intValue();
    }


    private Portfolio() {
        equity = null;
        debt = null;
        gold = null;
        rebalanced = false;
        LocalDate localDate = LocalDate.of(2022, 01, 01);
        currentMonth = localDate.getMonth();
    }


    @Override
    public Portfolio clone() {
        try {
            Portfolio clone = (Portfolio) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


