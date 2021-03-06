package com.keenant.flow.examples;

import com.keenant.flow.Column;
import com.keenant.flow.Cursor;
import com.keenant.flow.DatabaseContext;
import com.keenant.flow.EagerCursor;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.SelectScoped;
import com.keenant.flow.exp.FieldExp;
import java.util.stream.Stream;

import static com.keenant.flow.Flow.*;

public class SQLiteExample {

  private static final FieldExp USERS = field("users");
  private static final Column<Integer> ID = column(USERS, "id");
  private static final Column<String> NAME = column(USERS, "name");
  private static final Column<Integer> AGE = column(USERS, "age");

  public static void main(String[] args) throws Exception {
    try (DatabaseContext db = database(SQLDialect.SQLITE, "jdbc:sqlite:sample.db")) {
      db.prepareUpdate(
          "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), age INTEGER)")
          .executeAndClose();

      // Safely check number of users
      try (EagerCursor cursor = db.select(count()).from(USERS).fetch()) {
        long userCount = cursor.first().getNumber(1).orElse(0).longValue();

        // Add some data
        if (userCount == 0) {
          db.insertInto(USERS)
              .with(NAME, "Adam")
              .with(AGE, 21)
              .nextRecord()
              .with(NAME, "Jimmy")
              .with(AGE, 18)
              .execute()
              .close();
        }
      }

      // Raw query
      try (Cursor cursor = db.fetch("SELECT AVG(LENGTH(name)) FROM users")) {
        double value = cursor.next().getNonNullDouble(1);
        System.out.println("Average Name Length: " + value);
      }

      // Flow query
      try (Cursor cursor = db.select(avg(length(NAME))).from(USERS).fetch()) {
        double value = cursor.next().getNonNullDouble(1);
        System.out.println("Average Name Length: " + value);
      }

      // Raw stream
      String sql = "SELECT name, age FROM users WHERE id < ? ORDER BY age ASC";
      try (Stream<Cursor> stream = db.fetch(sql, 50).stream()) {
        stream.forEach(current -> {
          String name = current.getString("name").orElse("(No Name)");
          int age = current.getNonNullInt("age");
          System.out.println(name + " is " + age + " years old");
        });
      }

      // Flow stream
      SelectScoped query = db.select(NAME, AGE)
          .from(USERS)
          .where(ID.lt(50))
          .order(orderAsc(AGE));

      try (Stream<Cursor> stream = query.fetch().stream()) {
        stream.forEach(current -> {
          String name = current.getString("name").orElse("(No Name)");
          int age = current.getNonNullInt("age");
          System.out.println(name + " is " + age + " years old");
        });
      }

    }
  }
}
