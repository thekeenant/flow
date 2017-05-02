package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.Record;

import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractRecord implements Record {
    @Override
    public Optional<String> getString(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (String) obj);
    }

    @Override
    public Optional<Number> getNumber(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Number) obj);
    }

    @Override
    public Optional<Integer> getInt(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Integer) obj);
    }
}
