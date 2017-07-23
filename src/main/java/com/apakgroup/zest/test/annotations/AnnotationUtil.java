package com.apakgroup.zest.test.annotations;

import java.lang.annotation.Annotation;

import org.junit.Test;
import org.junit.Test.None;

public class AnnotationUtil {

    private AnnotationUtil() {
    }

    public static <T extends Throwable> Test testAnnotation() {
        return testAnnotation(None.class, 0L);
    }

    public static <T extends Throwable> Test testAnnotation(final long timeout) {
        return testAnnotation(None.class, timeout);
    }

    public static <T extends Throwable> Test testAnnotation(final Class<T> throwable, final long timeout) {
        return new Test() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Test.class;
            }

            @Override
            public Class<? extends Throwable> expected() {
                return throwable;
            }

            @Override
            public long timeout() {
                return timeout;
            }
        };
    }
}

