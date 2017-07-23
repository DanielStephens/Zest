package com.apakgroup.zest;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ObjWrapperAsserter extends AbstractAssert<ObjWrapperAsserter, ObjWrapper> {

    public ObjWrapperAsserter(final ObjWrapper actual) {
        super(actual, ObjWrapperAsserter.class);
    }

    public static ObjWrapperAsserter assertThat(final ObjWrapper actual) {
        return new ObjWrapperAsserter(actual);
    }

    @Override
    public ObjWrapperAsserter hasToString(final String value) {
        isNotNull();

        Assertions.assertThat(actual.toString()).isEqualTo(value);

        return this;
    }

    public ObjWrapperAsserter hasToStringEndingIn(final String value) {
        isNotNull();

        Assertions.assertThat(actual.toString()).endsWith(value);

        return this;
    }

    public ObjWrapperAsserter hasToStringStratingWith(final String value) {
        isNotNull();

        Assertions.assertThat(actual.toString()).startsWith(value);

        return this;
    }
}

