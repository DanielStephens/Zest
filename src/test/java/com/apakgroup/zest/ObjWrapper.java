package com.apakgroup.zest;

public class ObjWrapper {

    private final WrappedObj wrapped;

    public ObjWrapper(final WrappedObj wrapped) {
        this.wrapped = wrapped;
    }

    public void a() {
        wrapped.a();
    }

    public String getB() {
        return wrapped.getB();
    }

    public void throwsError() throws Throwable {
        throw new RuntimeException("throwing the usual!");
    }
}

