package com.keenant.flow;

import java.util.NoSuchElementException;

/**
 * A cursor that can traverse a result set more freely. This isn't supported by all databases.
 */
public interface EagerCursor extends Cursor {
    void moveTo(int record) throws NoSuchElementException;

    void moveToFirst() throws NoSuchElementException;

    void moveToLast() throws NoSuchElementException;

    Cursor move(int record) throws NoSuchElementException;

    Cursor first() throws NoSuchElementException;

    Cursor last() throws NoSuchElementException;
}
