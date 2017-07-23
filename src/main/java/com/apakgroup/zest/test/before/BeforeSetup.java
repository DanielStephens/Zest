package com.apakgroup.zest.test.before;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public interface BeforeSetup {

    public void before(final FrameworkMethod method, final Object target, final Statement statement);

}

