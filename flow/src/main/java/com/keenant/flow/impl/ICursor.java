package com.keenant.flow.impl;

import com.keenant.flow.Cursor;
import com.keenant.flow.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ICursor extends AbstractRecord implements Cursor {
    private final PreparedStatement statement;
    private final ResultSet resultSet;
    private ResultSetMetaData metaData;

    private Map<String, Integer> labelToIndex;

    public ICursor(PreparedStatement statement, ResultSet resultSet) {
        this.statement = statement;
        this.resultSet = resultSet;
    }

    protected ResultSet getResultSet() {
        return resultSet;
    }
    
    protected ResultSetMetaData getMetaData() {
        if (metaData == null) {
            try {
                metaData = resultSet.getMetaData();
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }
        return metaData;
    }

    @Override
    public boolean moveNext() {
        return moveNext(false);
    }

    private boolean moveNext(boolean ignoreInvalidation) {
        if (!ignoreInvalidation) {
            ensureValid();
        }

        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Cursor next() throws NoSuchElementException {
        if (!moveNext()) {
            throw new NoSuchElementException();
        }
        return this;
    }

    @Override
    public Stream<Cursor> stream() {
        Stream<Cursor> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED),false);
        stream = stream.onClose(this::close);
        return stream;
    }

    @Override
    public Iterator<Cursor> iterator() {
        return new CursorIterator();
    }

    @Override
    public void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    protected void ensureValid() {
        // Todo
    }

    private void invalidate() {
        // Todo
    }

    @Override
    public Optional<Object> get(int index) throws NoSuchElementException {
        if (!hasField(index))
            throw new NoSuchElementException();
        try {
            return Optional.ofNullable(resultSet.getObject(index));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private void createLabelToIndex() {
        if (labelToIndex == null) {
            try {
                labelToIndex = new HashMap<>();
                for (int i = 1; i <= getMetaData().getColumnCount(); i++) {
                    labelToIndex.put(getMetaData().getColumnLabel(i), i);
                }
            } catch (SQLException e) {
                throw new DatabaseException(e);
                // Todo
            }
        }
    }

    @Override
    public boolean hasField(int index) {
        try {
            return index >= 1 && index <= getMetaData().getColumnCount();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean hasField(String label) throws IllegalArgumentException {
        if (label == null)
            throw new IllegalArgumentException();

        createLabelToIndex();
        return labelToIndex.containsKey(label);
    }

    @Override
    public int getFieldIndex(String label) throws IllegalArgumentException, NoSuchElementException {
        if (label == null) {
            throw new IllegalArgumentException();
        }

        createLabelToIndex();
        Integer index = labelToIndex.get(label);

        if (index == null) {
            throw new NoSuchElementException();
        }

        return index;
    }

    @Override
    public String getFieldLabel(int index) throws NoSuchElementException {
        createLabelToIndex();
        for (String label : labelToIndex.keySet()) {
            int current = labelToIndex.get(label);
            if (current == index) {
                return label;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Iterates the cursor by advancing it each time {@link #next()} is called.
     */
    private class CursorIterator implements Iterator<Cursor> {
        /**
         * Indicates if there exists a record which {@link #next()} will return.
         */
        private boolean hasNext;

        /**
         * Indicates if {@link #next()} has been called already on the current record.
         */
        private boolean nextUsed = true;

        private CursorIterator() {
            invalidate();
        }

        @Override
        public boolean hasNext() {
            // Condition passes if hasNext() returned true, and a new record was found, and next() hasn't been called
            if (!nextUsed) {
                // Therefore, next() points to a record so hasNext() returns true.
                return true;
            }

            // Advance the cursor and store whether or not the next record exists
            hasNext = ICursor.this.moveNext(true);

            // If it does, indicate that next() hasn't been called, and should be called, in
            // order to advance the cursor to the next record.
            if (hasNext) {
                nextUsed = false;
            }

            return hasNext;
        }

        @Override
        public Cursor next() {
            // next() was already called once on this record, advance the record
            if (nextUsed) {
                hasNext = hasNext();
            }

            // Check if we are all done
            if (!hasNext) {
                throw new NoSuchElementException();
            }

            // indicate that next() has been called on this record
            nextUsed = true;

            return ICursor.this;
        }
    }
}
