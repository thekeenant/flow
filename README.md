# Flow

A simple SQL database library for Java that takes advantage of some of the awesome
functional features found in Java 8. It is designed to be:

* **Efficient**: Flow is a light-weight abstraction of JDBC that isn't too complex.
* **Simple**: Just add Flow as a dependency to your project and you are set. No fancy IDE
  or setup is required. No code generation is needed. It is just a Java library.
* **Powerful**: Flow does not limit you, you can still do anything you want with your database,
  even if that means writing raw SQL queries! With a single line you can perform complex operations 
  on a database, taking advantage of Java 8's `Stream`, and functional programming features.
* **Familiar**: Operations in Java using Flow resemble SQL to some degree. This makes 
* **Safe**: It is easy to make Flow do precisely what you want. Any exceptions you may
  encounter with Flow are designed to be easily handled and resolved.

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

* Databases
    * SQLite

Planned:
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
        Connector connector = Flow.connector("jdbc:sqlite:example.db");

        // An SQLDatabase interfaces with a database connection
        // Note: Try-with-resources used here to easily close the db after usage
        try (SQLDatabase db = Flow.open(SQLDialect.SQLITE, connector)) {
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