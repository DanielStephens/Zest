package com.apakgroup.zest.test.injection;

import java.lang.reflect.Field;
import java.util.Collection;

public interface FieldRetriever {

    Collection<Field> getFields(final Object object);

}

