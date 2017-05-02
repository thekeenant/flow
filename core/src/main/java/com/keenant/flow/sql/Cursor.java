package com.keenant.flow.sql;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Abstraction of a {@link java.sql.ResultSet}.
 */
public interface Cursor extends Record, AutoCloseable {
    /**
     * Moves the cursor to the next record.
     * @return true if the cursor points to a record after moving to the next.
     */
    boolean moveNext();

    /**
     * Moves the cursor to the next record.
     * @return the cursor instance itself.
     * @throws NoSuchElementException
     */
    Cursor next() throws NoSuchElementException;

    Stream<Cursor> stream();

    Iterator<Cursor> iterator();

    @Override
    void close();
}
