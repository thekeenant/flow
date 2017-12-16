package com.keenant.flow.examples;

import static com.keenant.flow.Flow.database;

import com.keenant.flow.Cursor;
import com.keenant.flow.DatabaseContext;
import com.keenant.flow.Query;
import com.keenant.flow.SQLDialect;
import java.util.stream.Stream;

public class MySQLExample {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    try (DatabaseContext db = database(SQLDialect.MYSQL,
        "jdbc:mysql://localhost/flow?user=root&password=password")) {
      Query query = db.prepareFetch("SELECT * FROM people");
      try (Stream<Cursor> stream = query.execute().eagerCursor().stream()) {
        stream.forEach(c -> System.out.println(c.toLabelMap()));
      }
    }
  }
}
