package com.apakgroup.zest.collections;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;

public class Collections3 {

    public static <A, B> List<B> transform(final List<A> fromList, final Function<A, B> transformer) {
        List<B> toList = new ArrayList<>(fromList.size());

        for (A a : fromList) {
            toList.add(transformer.apply(a));
        }

        return toList;
    }

}

