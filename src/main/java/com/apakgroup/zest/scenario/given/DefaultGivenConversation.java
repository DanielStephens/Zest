package com.apakgroup.zest.scenario.given;

import org.mockito.stubbing.Stubber;

public class DefaultGivenConversation implements GivenAnswer, GivenException, GivenNothing, GivenReturn {

    private final Stubber stub;
    
    public DefaultGivenConversation(final Stubber stub) {
        this.stub = stub;
    }

    @Override
    public <T> T isUsedWhenCalling(final T mock) {
        return stub.when(mock);
    }

    @Override
    public <T> T isThrownWhenCalling(final T mock) {
        return stub.when(mock);
    }

}

