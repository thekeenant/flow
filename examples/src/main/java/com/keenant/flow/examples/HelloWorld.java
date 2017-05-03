package com.keenant.flow.examples;

import com.keenant.flow.*;
import com.keenant.flow.impl.EnumTransformer;
import com.keenant.flow.impl.IColumn;
import com.keenant.flow.impl.exp.FieldExp;

import java.sql.*;
import java.util.stream.Stream;

import static com.keenant.flow.Flow.database;

public class HelloWorld {
    private static FieldExp USERS = new FieldExp("users");
    private static FieldExp ID = new FieldExp("id");
    private static FieldExp NAME = new FieldExp("name");
    private static FieldExp AGE = new FieldExp("age");

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

    enum NameEnum {
        Keenan,
        Adam
    }

    private static void test() {
        FieldExp users = new FieldExp("users");

        Transformer<String, NameEnum> blooper = new EnumTransformer<>(NameEnum.class);

        FieldExp id = new FieldExp(users, "id");
        Column<String, NameEnum> name = new IColumn<>(users, "name", blooper);

        // An SQLDatabase interfaces with a database connection
        // Note: Try-with-resources used here to easily close the db after usage
        try (DatabaseContext db = database(SQLDialect.SQLITE, "jdbc:sqlite:sample.db")) {
            // Here we assume that a table named "users" exists

            // Create a stream of records
            SelectScoped select = db.selectFrom(users).fields(name);

            // For each record
            try (EagerCursor cursor = select.fetch()) {
                while (cursor.moveNext()) {
                    
                }
            }
        }

    }
}
