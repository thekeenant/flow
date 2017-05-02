package com.keenant.flow.sql;

import java.util.NoSuchElementException;

public interface EagerCursor extends Cursor {
    void moveTo(int record) throws NoSuchElementException;

    void moveToFirst();

    void moveToLast();

    Cursor move(int record) throws NoSuchElementException;

    Cursor first();

    Cursor last();
}
