package com.keenant.flow.sql;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Represents a record/row in the database.
 */
public interface Record {
    /**
     * Check if a field exists.
     *
     * @param index the field index
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
     * @return the field index
     * @throws IllegalArgumentException if the field label provided is null
     * @throws NoSuchElementException if the field label is not present
     */
    int getFieldIndex(String label) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Get a field wrapped in an {@link Optional}, taking a null value possibility into account.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does not exist in the record
     */
    Optional<Object> get(int index) throws NoSuchElementException;

    /**
     * Get a string field.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does not exist in the record
     * @throws ClassCastException if the field was not the expected type
     */
    Optional<String> getString(int index) throws NoSuchElementException, ClassCastException;

    /**
     * Get a number field.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does not exist in the record
     * @throws ClassCastException if the field was not the expected type
     */
    Optional<Number> getNumber(int index) throws NoSuchElementException, ClassCastException;

    /**
     * Get an int field.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does not exist in the record
     * @throws ClassCastException if the field was not the expected type
     */
    Optional<Integer> getInt(int index) throws NoSuchElementException, ClassCastException;
}
