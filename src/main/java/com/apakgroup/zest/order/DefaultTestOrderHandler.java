package com.apakgroup.zest.order;

import com.apakgroup.zest.exceptions.BadTestOrdering;

public class DefaultTestOrderHandler implements TestOrderHandler {

    private int state = 0;

    @Override
    public void verifySetup() {
        updateState(1);
    }

    @Override
    public void verifyGiven() {
        updateState(2);
    }

    @Override
    public void verifyWhen() {
        updateState(3);
    }

    @Override
    public void verifyThen() {
        updateState(4);
    }

    @Override
    public void verifyRun() {
        updateState(5);
        state = 100;
    }

    private void updateState(final int newState) {
        if (newState == state || newState == state + 1) {
            state = newState;
            return;
        }

        throw new BadTestOrdering("arrrrggghhhhh!");
    }

}

