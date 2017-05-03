package com.keenant.flow.examples;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.Field;

import java.sql.*;
import java.util.Iterator;

import static com.keenant.flow.Flow.database;

public class HelloWorld {
    private static Field USERS = new Field("users");
    private static Field ID = new Field("id");
    private static Field NAME = new Field("name");
    private static Field AGE = new Field("age");

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void testJdbc() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void test() {

        // An SQLDatabase interfaces with a database connection
        // Note: Try-with-resources used here to easily close the db after usage
        try (DatabaseContext db = database(SQLDialect.SQLITE, "jdbc:sqlite:sample.db")) {
            try (Cursor cursor = db.fetch("SELECT * FROM users WHERE id = ? OR id = ?", 1, 2)) {
                // ...
            }
        }

    }
}
