package com.apakgroup.zest.builder;

import org.junit.runners.model.Statement;

import com.apakgroup.recall.setup.Recall;

public class TestStatement extends Statement {

    private final Recall recall;

    public TestStatement(final Recall recall) {
        this.recall = recall;
    }

    @Override
    public void evaluate() throws Throwable {
        recall.playback();
    }

}

