package com.apakgroup.zest.context;


public interface LoggableOutcome {

    void logFailure(Throwable e);

    void logSuccess();

}

