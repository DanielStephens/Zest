package com.apakgroup.zest.test.injection;


public interface FieldSetter<C, V> {

    void setFields(C object, V value);

}

