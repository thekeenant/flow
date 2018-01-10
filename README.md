# Flow 
[![Build Status](https://travis-ci.org/thekeenant/flow.svg?branch=master)](https://travis-ci.org/thekeenant/flow)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ff96f2fbc3894502a5acb846f3813a26)](https://www.codacy.com/app/thekeenant/flow?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=thekeenant/flow&amp;utm_campaign=Badge_Grade)

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
    * Nested queries
    * `GROUP BY`, `HAVING`, `JOIN`, `WHERE`, `ORDER BY`,
* Insert query
    * Multi insert
* Update query
* Delete query
* Databases (Tested)
    * MySQL
    * SQLite

Planned:
* UNION/INTERSECT/EXCEPT
* Create table query
* Check Postgres/other SQL databases
* Transactions

## Introduction

Connecting to a database is easy...

> _Note: We assume you have statically imported `com.keenant.flow.Flow.*`.
> This makes things much less verbose._

```java
DatabaseContext db = database(SQLDialect.SQLITE, "jdbc:sqlite:sample.db")

// access/manipulate 'db' here...

db.close(); // finally, close the connection(s) to the database
```

We tell Flow that this database speaks SQLite (as opposed to MySQL, or Postres for example).
Behind the scenes, the `database(...)` method constructs a default `Connector` implementation
that provides one single connection to a database based on the URL provided. Don't forget to 
close the database connection when you are done. This can be done with try-with-resources if
your prefer.

Let's perform a simple raw SQL query.

> _Note: Try-with-resources in Java 7 makes it easy to cleanly close objects that implement `AutoCloseable`.
> If you are not using try-with-resources, you must manually call `.close()` on the cursor once you are done._

```java
try (Cursor cursor = db.fetch("SELECT name FROM users WHERE id < ? AND name = ?", 10, "Jonathan")) {
    while (cursor.moveNext()) {
        String name = cursor.getNonNullString(1);
        System.out.println(name);
    }
}
```

This looks similar to JDBC's ResultSet, and it should. In order to maintain the performance of JDBC,
Flow keeps this lower level interface available.

The above example, as a stream...

```java
try (Stream<Cursor> stream = db.fetch("SELECT name FROM users WHERE id < ? AND name = ?", 10, "Jonathan").stream()) {
    stream.map(record -> record.getNonNullString(1)).forEach(System.out::println);
}
```

This is pretty nifty, but with Flow you can forget about raw SQL queries. This makes it easier and safer
to access and change database records.

```java
// These should probably be static, final somewhere in a "table"-like class.
Field users = field("users");
Column<Integer> id = column(users, "id", Integer.class);
Column<String> name = column(users, "name", String.class);

SelectScoped select = db.selectFrom(users).fields(name).where(id.lt(50).and(name.eq("Jonathan")));
try (Stream<Cursor> stream = select.fetch().stream()) {
    stream.map(record -> record.getNonNullString(1)).forEach(System.out::println);
}
```

Filters were used above in the `where` clause: `id.lt(50).and(name.eq("Jonathan"))`. They are pretty
straightforward. Here are some examples, with comments showing their SQL equivalent:

```java
// name = 'Adam'
Filter adam = name.eq("Adam");

// age <= 21
Filter young = age.lte(21);

// name = 'Adam' AND age <= 21
Filter youngAdam = adam.and(young);

// (name = 'Adam' AND age <= 21) OR age > 75
Filter youngAdamOrElderly = youngAdam.or(age.gt(75));

// LENGTH(name) >= 10
Filter longNames = length(name).gte(10);
```
