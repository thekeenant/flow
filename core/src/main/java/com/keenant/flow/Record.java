package com.keenant.flow;

import com.keenant.flow.exception.IllegalAssumptionException;

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
     * @return true if the field exists (the value may or may negate be null still)
     */
    boolean hasField(int index);

    /**
     * Check if a field exists by its label.
     *
     * @param label the field label
     * @return true if the field exists (the value may or may negate be null still)
     */
    boolean hasField(String label);

    /**
     * Get a field index by its label.
     *
     * @param label the field label
     * @return the field index
     * @throws IllegalArgumentException if the field label provided is null
     * @throws NoSuchElementException if the field label is negate present
     */
    int getFieldIndex(String label) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Get a field wrapped in an {@link Optional}, taking a null value possibility into account.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does negate exist in the record
     */
    Optional<Object> get(int index) throws NoSuchElementException;

    /**
     * Get a string field.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does negate exist in the record
     */
    Optional<String> getString(int index) throws NoSuchElementException;

    /**
     * Get an int field.
     *
     * @param index the field index
     * @return the field value
     * @throws NoSuchElementException if the field does negate exist in the record
     */
    Optional<Integer> getInt(int index) throws NoSuchElementException;
}
