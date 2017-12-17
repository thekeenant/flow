package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * An eager cursor that works on databases that don't support scroll insensitivity (SQLite).
 */
public class SafeEagerCursor extends EagerCursor {
  private final Statement statement;
  private final ResultSet resultSet;
  private ResultSetMetaData metaData;

  private List<Object[]> records;
  private int fieldCount;
  private Map<String, List<Integer>> labels;
  private int current;

  public SafeEagerCursor(PreparedStatement statement, ResultSet resultSet) {
    super(statement, resultSet);
    this.statement = statement;
    this.resultSet = resultSet;
  }

  private ResultSetMetaData getMetaData() {
    if (metaData == null) {
      try {
        metaData = resultSet.getMetaData();
      } catch (SQLException e) {
        throw new DatabaseException(e);
      }
    }
    return metaData;
  }

  void populateAndClose() {
    if (records == null || labels == null) {
      Map<String, List<Integer>> labels = new HashMap<>();
      List<Object[]> records = new ArrayList<>();
      int fieldCount = 0;

      try {
        // Labels
        for (int i = 1; i <= getMetaData().getColumnCount(); i++) {
          String label = getMetaData().getColumnLabel(i);
          List<Integer> existingIndexes = labels.getOrDefault(label, new ArrayList<>());
          existingIndexes.add(i);
          labels.put(label, existingIndexes);
          fieldCount++;
        }

        // Records
        while (resultSet.next()) {
          Object[] record = new Object[getMetaData().getColumnCount()];
          for (int i = 0; i < getMetaData().getColumnCount(); i++) {
            record[i] = resultSet.getObject(i + 1);
          }
          records.add(record);
        }
      } catch (SQLException e) {
        throw new DatabaseException(e);
      }

      this.records = records;
      this.labels = labels;
      this.fieldCount = fieldCount;
      close();
    }
  }

  private boolean hasRecord(int record) {
    return record >= 1 && record <= records.size();
  }

  private Object[] getCurrentRecord() {
    return records.get(current - 1);
  }

  @Override
  public void moveTo(int record) throws NoSuchElementException {
    if (!hasRecord(record)) {
      throw new NoSuchElementException();
    }
    current = record;
  }

  @Override
  public void moveToFirst() throws NoSuchElementException {
    if (!hasRecord(1)) {
      throw new NoSuchElementException();
    }
    current = 1;
  }

  @Override
  public void moveToLast() throws NoSuchElementException {
    if (!hasRecord(1)) {
      throw new NoSuchElementException();
    }
    current = records.size();
  }

  @Override
  public EagerCursor move(int record) throws NoSuchElementException {
    moveTo(record);
    return this;
  }

  @Override
  public EagerCursor first() throws NoSuchElementException {
    moveToFirst();
    return this;
  }

  @Override
  public EagerCursor last() throws NoSuchElementException {
    moveToLast();
    return this;
  }

  @Override
  public boolean hasField(int index) {
    return index >= 1 && index <= fieldCount;
  }

  @Override
  public boolean hasField(String label) throws IllegalArgumentException {
    return labels.containsKey(label);
  }

  @Override
  public int getFieldIndex(String label) throws IllegalArgumentException, NoSuchElementException {
    List<Integer> indexes = labels.get(label);
    if (label == null)
      throw new IllegalArgumentException("Label cannot be null");
    if (indexes == null)
      throw new NoSuchElementException();
    if (indexes.size() > 1)
      throw new IllegalArgumentException("Label maps to multiple indexes");
    return labels.get(label).get(0);
  }

  @Override
  public String getFieldLabel(int index) throws NoSuchElementException {
    for (String label : labels.keySet()) {
      int current = getFieldIndex(label);
      if (current == index) {
        return label;
      }
    }
    throw new NoSuchElementException();
  }

  @Override
  public Optional<Object> get(int index) throws NoSuchElementException {
    return Optional.ofNullable(getCurrentRecord()[index - 1]);
  }

  @Override
  public boolean moveNext() {
    current++;
    return hasRecord(current);
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
    Stream<Cursor> stream = StreamSupport
        .stream(Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED), false);
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
      statement.close();
      resultSet.close();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  private class CursorIterator implements Iterator<Cursor> {

    @Override
    public boolean hasNext() {
      return hasRecord(current + 1);
    }

    @Override
    public Cursor next() {
      return SafeEagerCursor.this.next();
    }
  }
}
