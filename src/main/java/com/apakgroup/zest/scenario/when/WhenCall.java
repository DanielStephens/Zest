package com.apakgroup.zest.scenario.when;

public class WhenCall<T> {

    private final T topic;

    public WhenCall(final T topic) {
        this.topic = topic;
    }

    public T calls() {
        return topic;
    }
}

