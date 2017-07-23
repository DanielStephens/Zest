package com.apakgroup.zest.scenario.then;

import org.assertj.core.api.AbstractAssert;

public interface ThenUseAssertJ<A extends AbstractAssert<SELF, ACTUAL>, SELF extends AbstractAssert<SELF, ACTUAL>, ACTUAL> {

    public A that(final ACTUAL object);
}

