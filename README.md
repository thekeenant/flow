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
    public static void main(String[] args) {
        // A connector provides a database connection
        // Note: Statically imported Flow.*
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
```