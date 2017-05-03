package com.keenant.flow;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Represents a record/row in the database.
 */
public interface Record {
    /**
     * Check if a field exists.
     *
     * @param index the field
     * @return true if the field exists (the value may or may not be null still)
     */
    boolean hasField(int index);

    /**
     * Check if a field exists by its label.
     *
     * @param label the field label
     * @throws IllegalArgumentException if the field label provided is null
     * @return true if the field exists (the value may or may not be null still)
     */
    boolean hasField(String label) throws IllegalArgumentException;

    /**
     * Get a field index by its label.
     *
     * @param label the field label
     * @return the field
     * @throws IllegalArgumentException if the field label provided is null
     * @throws NoSuchElementException if the field label is not present
     */
    int getFieldIndex(String label) throws IllegalArgumentException, NoSuchElementException;

    Optional<Object> get(int index) throws NoSuchElementException;

    Optional<Object> get(String label) throws NoSuchElementException;

    Object getNonNull(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Object getNonNull(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<String> getString(int index) throws NoSuchElementException, ClassCastException;

    Optional<String> getString(String label) throws NoSuchElementException, ClassCastException;

    String getNonNullString(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    String getNonNullString(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Number> getNumber(int index) throws NoSuchElementException, ClassCastException;

    Optional<Number> getNumber(String label) throws NoSuchElementException, ClassCastException;

    Number getNonNullNumber(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Number getNonNullNumber(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Integer> getInt(int index) throws NoSuchElementException, ClassCastException;

    Optional<Integer> getInt(String label) throws NoSuchElementException, ClassCastException;

    int getNonNullInt(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    int getNonNullInt(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Boolean> getBoolean(int index) throws NoSuchElementException, ClassCastException;

    Optional<Boolean> getBoolean(String label) throws NoSuchElementException, ClassCastException;

    boolean getNonNullBoolean(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    boolean getNonNullBoolean(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Date> getDate(int index) throws NoSuchElementException, ClassCastException;

    Optional<Date> getDate(String label) throws NoSuchElementException, ClassCastException;

    Date getNonNullDate(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Date getNonNullDate(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Time> getTime(int index) throws NoSuchElementException, ClassCastException;

    Optional<Time> getTime(String label) throws NoSuchElementException, ClassCastException;

    Time getNonNullTime(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Time getNonNullTime(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Optional<Timestamp> getTimestamp(int index) throws NoSuchElementException, ClassCastException;

    Optional<Timestamp> getTimestamp(String label) throws NoSuchElementException, ClassCastException;

    Timestamp getNonNullTimestamp(int index) throws NoSuchElementException, ClassCastException, IllegalStateException;

    Timestamp getNonNullTimestamp(String label) throws NoSuchElementException, ClassCastException, IllegalStateException;
}
