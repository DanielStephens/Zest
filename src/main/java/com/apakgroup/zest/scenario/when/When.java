package com.apakgroup.zest.scenario.when;

public interface When {

    void nothingHappens();

    <T> WhenCall<T> object(T realObject);
}

