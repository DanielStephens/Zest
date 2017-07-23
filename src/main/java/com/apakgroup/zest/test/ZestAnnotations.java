package com.apakgroup.zest.test;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class ZestAnnotations {

    public static void init(final FrameworkMethod method, final Object target, final Statement statement) {
        ZestTestUtil.zestTestInjector().before(method, target, statement);
    }
}

