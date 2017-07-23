package com.apakgroup.zest.scenario.given;


public interface GivenException {

    <T> T isThrownWhenCalling(T mock);

}

