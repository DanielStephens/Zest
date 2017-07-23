package com.apakgroup.zest.test.filters;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.runners.RunnerImpl;
import org.mockito.internal.runners.util.FrameworkUsageValidator;

import com.apakgroup.zest.test.ZestAnnotations;
import com.apakgroup.zest.test.ZestContext;
import com.apakgroup.zest.test.annotations.definitions.AnnotationDefinitionsUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class MockitoFilter implements RunnerImpl {

    private final BlockJUnit4ClassRunner runner;

    private static ZestContext zestContext;

    public MockitoFilter(final Class<?> klass) throws InitializationError {
        runner = new BlockJUnit4ClassRunner(klass) {

            @Override
            protected Statement withBefores(final FrameworkMethod method, final Object target,
                    final Statement statement) {
                ZestAnnotations.init(method, target, statement);
                MockitoAnnotations.initMocks(target);
                return super.withBefores(method, target, statement);
            }

            @Override
            protected Statement withAfters(final FrameworkMethod method, final Object target,
                    final Statement statement) {
                Statement s = new Statement() {

                    @Override
                    public void evaluate() throws Throwable {
                        List<Throwable> errors = new ArrayList<Throwable>();
                        try {
                            statement.evaluate();
                        } catch (Throwable e) {
                            errors.add(e);
                        } finally {
                            if (zestContext != null) {
                                zestContext.currentScenario().runTest();
                            }
                        }
                        MultipleFailureException.assertEmpty(errors);
                    }
                };
                return super.withAfters(method, target, s);
            }
            
            @Override
            protected List<FrameworkMethod> computeTestMethods() {
                Collection<FrameworkMethod> allMethods = getTestClass().getAnnotatedMethods();
                return new ArrayList<>(Collections2.filter(allMethods, methodFilter()));
            }
            
            private Predicate<FrameworkMethod> methodFilter(){
                return new Predicate<FrameworkMethod>(){

                    @Override
                    public boolean apply(final FrameworkMethod input) {
                        for (Class<? extends Annotation> clazz : AnnotationDefinitionsUtil.getTestMethodAnnotations()) {
                            if(input.getAnnotation(clazz) != null){
                                return true;
                            }
                        }
                        
                        return false;
                    }
                    
                };
            }

        };
    }

    private <A extends Annotation> List<FrameworkMethod> resolveFrameworkMethods(final TestClass testClass,
            final Class<A> clazz) {
        List<FrameworkMethod> testMethods = testClass.getAnnotatedMethods(clazz);
        List<FrameworkMethod> mockedTestMethods = new ArrayList<>(testMethods.size());

        for (FrameworkMethod testMethod : testMethods) {
            FrameworkMethod mock = Mockito.spy(testMethod);
            Mockito.doReturn(AnnotationDefinitionsUtil.buildTestFrom(testMethod.getAnnotation(clazz))).when(mock)
                    .getAnnotation(Matchers.eq(Test.class));
            mockedTestMethods.add(mock);
        }

        return mockedTestMethods;

    }

    @Override
    public void run(final RunNotifier notifier) {
        // add listener that validates framework usage at the end of each test
        notifier.addListener(new FrameworkUsageValidator(notifier));

        runner.run(notifier);
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }

    @Override
    public void filter(final Filter filter) throws NoTestsRemainException {
        runner.filter(filter);
    }

    public static void setZestContext(final ZestContext zestContext) {
        MockitoFilter.zestContext = zestContext;
    }

}

