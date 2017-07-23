package com.apakgroup.zest.builder;

import org.junit.runners.model.Statement;

public class DoNothingStatement extends Statement {

    @Override
    public void evaluate() throws Throwable {
        return;
    }

}

