package com.keenant.flow;

import java.util.Optional;
import java.util.function.Function;

public interface Transformer<T, U> {
    static <T, U> Transformer<T, U> of(Function<T, U> to, Function<U, T> from, Class<T> sourceType, Class<U> type) {
        return  new Transformer<T, U>() {
            @Override
            public U to(T sourceObject) {
                return to.apply(sourceObject);
            }

            @Override
            public T from(U object) {
                return from.apply(object);
            }

            @Override
            public Class<T> getSourceType() {
                return sourceType;
            }

            @Override
            public Class<U> getType() {
                return type;
            }
        };
    }

    U to(T sourceObject);

    T from(U object);

    Class<T> getSourceType();

    Class<U> getType();

    @SuppressWarnings("unchecked")
    default Optional<U> toOptional(Object sourceObject) {
        if (sourceObject == null) {
            return Optional.empty();
        }
        if (!getSourceType().isInstance(sourceObject)) {
            throw new ClassCastException();
        }
        return Optional.of(to((T) sourceObject));
    }

    @SuppressWarnings("unchecked")
    default Optional<T> fromOptional(Object object) {
        if (object == null) {
            return Optional.empty();
        }
        if (!getType().isInstance(object)) {
            throw new ClassCastException();
        }
        return Optional.of(from((U) object));
    }
}
