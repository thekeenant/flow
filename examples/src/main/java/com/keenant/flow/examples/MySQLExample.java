package com.keenant.flow.examples;

import com.keenant.flow.Column;
import com.keenant.flow.Cursor;
import com.keenant.flow.DatabaseContext;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.exp.Field;

import static com.keenant.flow.Flow.*;

public class MySQLExample {
    private static final Field USERS = field("users");
    private static final Column<Integer> ID = column(USERS, "id", Integer.class);
    private static final Column<String> NAME = column(USERS, "name", String.class);
    private static final Column<Integer> AGE = column(USERS, "age", Integer.class);

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        try (DatabaseContext db = database(SQLDialect.MYSQL, "jdbc:mysql://localhost/flow?user=root&password=password")) {
            try (Cursor cursor = db.selectFrom(field("people")).fetch()) {
                while (cursor.moveNext()) {
                    System.out.println(cursor.get(1));
                    System.out.println(cursor.get(2));
                    System.out.println(cursor.get(3));
                }
            }
        }
    }
}
