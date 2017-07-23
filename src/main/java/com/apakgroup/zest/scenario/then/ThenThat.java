package com.apakgroup.zest.scenario.then;

import org.assertj.core.api.AbstractAssert;

import com.apakgroup.recall.defaults.ConstructorObjectBuilder;
import com.apakgroup.recall.defaults.DefaultObjectBuilder;
import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.defaults.NullBuilder;
import com.apakgroup.recall.defaults.RealValueOrDefaultBuilder;
import com.apakgroup.recall.defaults.StaticMethodObjectBuilder;
import com.apakgroup.recall.defaults.builder.DefaultResolverChain;
import com.apakgroup.recall.reflection.ConstructorResolver;

public class ThenThat<A extends AbstractAssert<SELF, ACTUAL>, SELF extends AbstractAssert<SELF, ACTUAL>, ACTUAL>
        implements ThenUseAssertJ<A, SELF, ACTUAL> {

    private final Class<A> asserterClass;

    public ThenThat(final Class<A> asserterClass) {
        this.asserterClass = asserterClass;
    }


    @Override
    public A that(final ACTUAL object) {
        DefaultResolver internalBuilder = DefaultResolverChain.use(new RealValueOrDefaultBuilder(object))
                .or(new NullBuilder());

        DefaultResolver defaultBuilder = DefaultResolverChain.use(new DefaultObjectBuilder())
                .or(new ConstructorObjectBuilder(new ConstructorResolver(), internalBuilder))
                .or(new StaticMethodObjectBuilder(internalBuilder))
                .or(new NullBuilder());
        
        return defaultBuilder.generateDefaultFor(asserterClass);
    }
}

