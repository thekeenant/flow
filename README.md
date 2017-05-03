# Flow

_A successor to the Quest database library ([v1](https://github.com/thekeenant/quest), 
[v2](https://github.com/thekeenant/quest-v2))._

A simple SQL database library for Java that takes advantage of some of the awesome
functional features found in Java 8. It is designed to be:

* **Powerful**: Flow does not limit you, you can still do anything you want with your database,
  even if that means writing raw SQL queries! With a single line you can perform complex operations 
  on a database, taking advantage of Java 8's `Stream`, and functional programming features.
* **Simple**: Just add Flow as a dependency to your project and you are set. No fancy IDE
  or setup is required. No code generation is needed. It is just a Java library.
* **Familiar**: Operations in Java using Flow resemble SQL to some degree. This makes working with
  databases fun and easy!
* **Safe**: Making Flow do precisely what you want is straightforward. It's easy to recognize errors
  in queries during development and fix them before they cost your project or company time and/or money.
* **Efficient**: Flow is a light-weight abstraction of JDBC. Thus, it inherently has increased time
  and memory complexity. We have yet to test Flow against other libraries, but our goal here is 
  to make queries stay within 5-10% the memory usage and performance of JDBC.

Flow is not complete. It also lacks documentation, and as such, features will break upon every 
version iteration until a full release.

## Roadmap

Supported:

* Select query
    * Specify fields
    * Filters
    * Order
* Insert query
    * Multi insert
* Databases (Tested)
    * SQLite

Planned:
* Column API (for pseudo type-safe queries)
* Update query
* Delete query
* Create table query
* Databases
    * MySQL
    * Postgres

## Introduction

For now you must provide a database connection yourself. Below is a simple SQLite example.

```java
public class Example {
    private static final Field USERS = field("users");
    private static final Field ID = USERS.column("id");
    private static final Field NAME = USERS.column("name");
    private static final Field AGE = USERS.column("age");

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
        }
    }
}

```