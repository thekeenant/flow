package com.keenant.flow.impl;

import com.keenant.flow.Column;
import com.keenant.flow.Record;
import com.keenant.flow.Transformer;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractRecord implements Record {
    @Override
    public <T, U> Optional<U> get(Column<T, U> column) throws NoSuchElementException, ClassCastException {
        return get(getFieldIndex(column.getName()), column);
    }

    @Override
    public <T, U> U getNonNull(Column<T, U> column) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNull(getFieldIndex(column.getName()), column);
    }

    @Override
    public <T, U> Optional<U> get(int field, Transformer<T, U> transformer) throws NoSuchElementException, ClassCastException {
        Object sourceObj = get(field).orElse(null);
        return transformer.toOptional(sourceObj);
    }

    @Override
    public <T, U> Optional<U> get(String label, Transformer<T, U> transformer) throws NoSuchElementException, ClassCastException {
        return get(getFieldIndex(label), transformer);
    }

    @Override
    public <T, U> U getNonNull(int field, Transformer<T, U> transformer) throws NoSuchElementException, ClassCastException, IllegalStateException {
        Object sourceObj = getNonNull(field);
        return transformer.toOptional(sourceObj).orElseThrow(IllegalStateException::new);
    }

    @Override
    public <T, U> U getNonNull(String label, Transformer<T, U> transformer) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNull(getFieldIndex(label),  transformer);
    }

    @Override
    public Optional<Object> get(int index) throws NoSuchElementException {
        return null;
    }

    @Override
    public Optional<Object> get(String label) throws NoSuchElementException {
        return get(getFieldIndex(label));
    }

    @Override
    public Object getNonNull(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return get(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Object getNonNull(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNull(getFieldIndex(label));
    }

    @Override
    public Optional<String> getString(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (String) obj);
    }

    @Override
    public Optional<String> getString(String label) throws NoSuchElementException, ClassCastException {
        return getString(getFieldIndex(label));
    }

    @Override
    public String getNonNullString(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getString(index).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public String getNonNullString(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullString(getFieldIndex(label));
    }

    @Override
    public Optional<Number> getNumber(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Number) obj);
    }

    @Override
    public Optional<Number> getNumber(String label) throws NoSuchElementException, ClassCastException {
        return getNumber(getFieldIndex(label));
    }

    @Override
    public Number getNonNullNumber(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNumber(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Number getNonNullNumber(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullNumber(getFieldIndex(label));
    }

    @Override
    public Optional<Integer> getInt(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Integer) obj);
    }

    @Override
    public Optional<Integer> getInt(String label) throws NoSuchElementException, ClassCastException {
        return getInt(getFieldIndex(label));
    }

    @Override
    public int getNonNullInt(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getInt(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public int getNonNullInt(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullInt(getFieldIndex(label));
    }

    @Override
    public Optional<Boolean> getBoolean(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Boolean) obj);
    }

    @Override
    public Optional<Boolean> getBoolean(String label) throws NoSuchElementException, ClassCastException {
        return getBoolean(getFieldIndex(label));
    }

    @Override
    public boolean getNonNullBoolean(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getBoolean(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean getNonNullBoolean(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullBoolean(getFieldIndex(label));
    }

    @Override
    public Optional<Date> getDate(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Date) obj);
    }

    @Override
    public Optional<Date> getDate(String label) throws NoSuchElementException, ClassCastException {
        return getDate(getFieldIndex(label));
    }

    @Override
    public Date getNonNullDate(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getDate(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Date getNonNullDate(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullDate(getFieldIndex(label));
    }

    @Override
    public Optional<Time> getTime(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Time) obj);
    }

    @Override
    public Optional<Time> getTime(String label) throws NoSuchElementException, ClassCastException {
        return getTime(getFieldIndex(label));
    }

    @Override
    public Time getNonNullTime(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getTime(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Time getNonNullTime(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullTime(getFieldIndex(label));
    }

    @Override
    public Optional<Timestamp> getTimestamp(int index) throws NoSuchElementException, ClassCastException {
        return get(index).map(obj -> (Timestamp) obj);
    }

    @Override
    public Optional<Timestamp> getTimestamp(String label) throws NoSuchElementException, ClassCastException {
        return getTimestamp(getFieldIndex(label));
    }

    @Override
    public Timestamp getNonNullTimestamp(int index) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getTimestamp(index).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Timestamp getNonNullTimestamp(String label) throws NoSuchElementException, ClassCastException, IllegalStateException {
        return getNonNullTimestamp(getFieldIndex(label));
    }
}
