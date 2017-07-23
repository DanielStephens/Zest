package com.apakgroup.zest.test.injection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultAnnotatedFieldRetriever<A extends Annotation, F> implements FieldRetriever {

    private final Class<A> annotationClass;

    private final Class<F> fieldClass;

    public DefaultAnnotatedFieldRetriever(final Class<A> annotationClass, final Class<F> fieldClass) {
        this.annotationClass = annotationClass;
        this.fieldClass = fieldClass;
    }

    @Override
    public Collection<Field> getFields(final Object object) {
        Collection<Field> applicableFields = new ArrayList<>();

        for (Field field : resolveAllFields(object)) {
            if (!isApplicable(field)) {
                continue;
            }

            applicableFields.add(field);
        }

        return Collections.unmodifiableCollection(applicableFields);
    }

    private Collection<Field> resolveAllFields(final Object object) {
        List<Field> fields = new ArrayList<>();

        Class<?> clazz = object.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    private boolean isApplicable(final Field field) {
        if (field.getAnnotation(annotationClass) == null) {
            return false;
        }

        if (!fieldClass.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

}

