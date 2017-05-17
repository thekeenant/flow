package com.keenant.flow;

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

    /**
     * Create a stream from this cursor.
     * @return the stream
     */
    Stream<Cursor> stream();

    /**
     * Create an iterator from this cursor.
     * @return the iterator
     */
    Iterator<Cursor> iterator();

    @Override
    void close();
}
