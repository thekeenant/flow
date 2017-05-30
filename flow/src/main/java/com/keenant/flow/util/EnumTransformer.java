package com.keenant.flow.util;

import com.keenant.flow.util.Transformer;

public class EnumTransformer<T extends Enum<T>> implements Transformer<String, T> {
    private final Class<T> enumType;

    public EnumTransformer(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T to(String sourceObject) {
        return Enum.valueOf(getType(), sourceObject);
    }

    @Override
    public String from(T object) {
        return object.name();
    }

    @Override
    public Class<String> getSourceType() {
        return String.class;
    }

    @Override
    public Class<T> getType() {
        return enumType;
    }
}
