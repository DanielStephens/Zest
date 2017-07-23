package com.apakgroup.zest.scenario.given;


public interface GivenAnswer {

    <T> T isUsedWhenCalling(T mock);
}

