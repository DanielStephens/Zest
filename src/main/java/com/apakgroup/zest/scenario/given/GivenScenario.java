package com.apakgroup.zest.scenario.given;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

public class GivenScenario implements Given {

    @Override
    public GivenException theThrowable(final Throwable throwable) {
        return new DefaultGivenConversation(Mockito.doThrow(throwable));
    }

    @Override
    public GivenReturn theValue(final Object returnedObject) {
        return new DefaultGivenConversation(Mockito.doReturn(returnedObject));
    }

    @Override
    public GivenNothing nothingHappens() {
        return new DefaultGivenConversation(Mockito.doNothing());
    }

    @Override
    public <T> GivenAnswer theAnswer(final Answer<T> answer) {
        return new DefaultGivenConversation(Mockito.doAnswer(answer));
    }

    @Override
    public GivenAnswer theRealMethod() {
        return new DefaultGivenConversation(Mockito.doCallRealMethod());
    }

}

