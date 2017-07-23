package com.apakgroup.zest.scenario.then;


public interface ThenAssert {

    void orFailWithMessage(String message, Object... params);

    void orFailWithDefaultMessage();
}

