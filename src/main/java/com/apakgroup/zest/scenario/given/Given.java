package com.apakgroup.zest.scenario.given;

import org.mockito.stubbing.Answer;

public interface Given {

    GivenException theThrowable(Throwable throwable);

    GivenReturn theValue(Object returnedObject);

    GivenNothing nothingHappens();

    <T> GivenAnswer theAnswer(Answer<T> answer);

    GivenAnswer theRealMethod();
}

