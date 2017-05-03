package com.keenant.flow.examples;

import static com.keenant.flow.Flow.*;
import com.keenant.flow.*;
import com.keenant.flow.impl.exp.FieldExp;

import java.util.stream.Stream;

public class HelloWorld {
    private static FieldExp USERS = new FieldExp("users");
    private static FieldExp ID = new FieldExp("id");
    private static FieldExp NAME = new FieldExp("name");
    private static FieldExp AGE = new FieldExp("age");

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() {
        // A connector provides a database connection
        Connector connector = connector("jdbc:sqlite:sample.db");

        // An SQLDatabase interfaces with a database connection
        // Note: Try-with-resources used here to easily close the db after usage
        try (DatabaseContext db = database(SQLDialect.SQLITE, connector)) {
            // Here we assume that a table named "users" exists
            FieldExp users = new FieldExp("users");

            // Create a stream of records
            Stream<Cursor> result = db.selectFrom(users).fetch().stream();

            // For each record
            result.forEach(current -> {
                // Print out the first field
                System.out.println(current.get(1));
            });
        }

    }
}
