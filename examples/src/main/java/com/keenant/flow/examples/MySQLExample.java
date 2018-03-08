package com.keenant.flow.examples;

import com.keenant.flow.*;
import com.keenant.flow.exp.FieldExp;

import java.util.stream.Stream;

import static com.keenant.flow.Flow.database;
import static com.keenant.flow.Flow.field;

public class MySQLExample {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    try (DatabaseContext db = database(SQLDialect.MYSQL,
        "jdbc:mysql://localhost/madgrades_dev?user=root&password=password")) {

      Query query = db.prepareFetch("SELECT * FROM courses");
      try (Stream<Cursor> stream = query.execute().eagerCursor().stream()) {
        stream.forEach(c -> System.out.println(c.getFieldLabel(2)));
      }

      FieldExp users = field("users");
      Column<Integer> id = new Column<>(users, "id", Integer.class);












    }
  }
}
