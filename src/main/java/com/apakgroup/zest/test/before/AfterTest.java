package com.apakgroup.zest.test.before;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public interface AfterTest {

    public void after(final FrameworkMethod method, final Object target, final Statement statement);

}

