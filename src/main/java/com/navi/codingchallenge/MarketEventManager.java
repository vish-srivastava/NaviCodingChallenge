package com.navi.codingchallenge;

import com.navi.codingchallenge.managers.Implementations.*;
import com.navi.codingchallenge.models.EventType;
import com.navi.codingchallenge.models.Portfolio;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarketEventManager {

    private Portfolio portfolio;
    private PortfolioAllocationManager portfolioAllocationManager;
    private SIPManager sipManager;
    private MarketChangeHandler marketChangeHandler;
    private PortfolioAuditManager portfolioAuditManager;
    private PortfolioRebalancingManager rebalancingManager;

    public MarketEventManager() {
        portfolioAllocationManager = new PortfolioAllocationManager();
        sipManager = new SIPManager();
        marketChangeHandler = new MarketChangeHandler();
        portfolioAuditManager = new PortfolioAuditManager();
        rebalancingManager = new PortfolioRebalancingManager();
    }

    private Map<EventType, LinkedList<MarketEventListener>> listeners;

    public void subscribe(EventType eventType, MarketEventListener listener) {
        LinkedList<MarketEventListener> users = listeners.containsKey(eventType) ? listeners.get(eventType) : new LinkedList<>();
        users.add(listener);
        listeners.put(eventType, users);
    }

    public void unsubscribe(EventType eventType, MarketEventListener listener) {
        LinkedList<MarketEventListener> users = listeners.get(eventType);
        users.remove(listener);
        listeners.remove(eventType);
    }

    public void notifyListener(EventType eventType, String[] event) throws Exception {
        List<MarketEventListener> users = listeners.get(eventType);
        for (MarketEventListener listener : users) {
            this.portfolio = listener.handleEvent(eventType, this.portfolio, event);
        }
    }

    public void registerEventListeners() {
        listeners = new TreeMap<>();
        subscribe(EventType.ALLOCATE, portfolioAllocationManager);
        subscribe(EventType.SIP, sipManager);
        subscribe(EventType.CHANGE, sipManager);
        subscribe(EventType.CHANGE, marketChangeHandler);
        subscribe(EventType.CHANGE, portfolioAuditManager);
        subscribe(EventType.BALANCE, portfolioAuditManager);
        subscribe(EventType.REBALANCE, rebalancingManager);
        subscribe(EventType.REBALANCE, portfolioAuditManager);
    }

    public boolean isPortfolioAllocated() {
        if (portfolio != null) {
            return true;
        }
        return false;
    }

}
