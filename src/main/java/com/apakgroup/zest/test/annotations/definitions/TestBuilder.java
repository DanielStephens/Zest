package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.Annotation;

import org.junit.Test;

public interface TestBuilder<A extends Annotation> {

    public Test convert(A annotation);

}
