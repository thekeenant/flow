package com.keenant.flow.examples;

import com.keenant.flow.*;
import com.keenant.flow.exp.Field;

import java.util.stream.Stream;

import static com.keenant.flow.Flow.*;

public class SQLiteExample {
    private static final Field USERS = field("users");
    private static final Column<Integer> ID = column(USERS, "id", Integer.class);
    private static final Column<String> NAME = column(USERS, "name", String.class);
    private static final Column<Integer> AGE = column(USERS, "age", Integer.class);

    public static void main(String[] args) throws Exception {
        try (DatabaseContext db = database(SQLDialect.SQLITE, "jdbc:sqlite:sample.db")) {
            // Raw query
            try (Cursor cursor = db.fetch("SELECT AVG(LENGTH(name)) FROM users")) {
                double value = cursor.next().getNonNullDouble(1);
                System.out.println("Average Name Length: " + value);
            }

            // Flow query
            try (Cursor cursor = db.selectFrom(USERS).fields(avg(length(NAME))).fetch()) {
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
            SelectScoped query = db.selectFrom(USERS).fields(NAME, AGE).where(ID.lt(50)).order(orderAsc(AGE));
            try (Stream<Cursor> stream = query.fetch().stream()) {
                stream.forEach(current -> {
                    String name = current.getString("name").orElse("(No Name)");
                    int age = current.getNonNullInt("age");
                    System.out.println(name + " is " + age + " years old");
                });
            }

            // name = 'Adam'
            Filter adam = NAME.eq("Adam");

            // age <= 21
            Filter young = AGE.lte(21);

            // name = 'Adam' AND age <= 21
            Filter youngAdam = adam.and(young);

            // (name = 'Adam' AND age < 21) OR age > 75
            Filter youngAdamOrElderly = youngAdam.or(AGE.gt(75));

            // LENGTH(name) >= 10
            Filter longNames = length(NAME).gte(10);
        }
    }
}
