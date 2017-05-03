package com.keenant.flow;

import java.util.NoSuchElementException;

/**
 * A cursor that can traverse a result set more freely. This isn't supported by all databases.
 */
public interface EagerCursor extends Cursor {
    void moveTo(int record) throws NoSuchElementException;

    void moveToFirst();

    void moveToLast();

    Cursor move(int record) throws NoSuchElementException;

    Cursor first();

    Cursor last();
}
