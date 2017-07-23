package com.apakgroup.zest.test.injection;

import java.lang.reflect.Field;

public class DefaultFieldSetter<F> implements FieldSetter<Object, F> {

    private final FieldRetriever fieldRetriever;

    public DefaultFieldSetter(final FieldRetriever fieldRetriever) {
        this.fieldRetriever = fieldRetriever;
    }

    @Override
    public void setFields(final Object object, final F value) {
        for (Field field : fieldRetriever.getFields(object)) {
            setField(object, field, value);
        }
    }

    private void setField(final Object object, final Field field, final F value) {
        boolean accessibility = field.isAccessible();

        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            field.setAccessible(accessibility);
        }
    }

}

