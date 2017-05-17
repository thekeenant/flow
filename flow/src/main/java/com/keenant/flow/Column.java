package com.keenant.flow;

public interface Column<T> extends MappedColumn<T, T>  {
    @Override
    default T from(T object) {
        return object;
    }

    @Override
    default T to(T sourceObject) {
        return sourceObject;
    }

    @Override
    default Class<T> getSourceType() {
        return getType();
    }
}
