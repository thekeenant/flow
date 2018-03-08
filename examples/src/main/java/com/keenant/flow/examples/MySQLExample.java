package com.keenant.flow.examples;

import static com.keenant.flow.Flow.alias;
import static com.keenant.flow.Flow.count;
import static com.keenant.flow.Flow.database;
import static com.keenant.flow.Flow.max;
import static com.keenant.flow.Flow.select;
import static com.keenant.flow.examples.Schema.COURSES;
import static com.keenant.flow.examples.Schema.COURSE_OFFERINGS;
import static com.keenant.flow.examples.Schema.SECTIONS;

import com.keenant.flow.Cursor;
import com.keenant.flow.DatabaseContext;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.Select;
import com.keenant.flow.SelectScoped;
import com.keenant.flow.exp.AliasExp;

public class MySQLExample {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    try (DatabaseContext db = database(SQLDialect.MYSQL,
        "jdbc:mysql://localhost/madgrades_dev?user=root&password=password")) {

      AliasExp sectionCount = alias("section_count");

      SelectScoped query = db.
          select(COURSES.UUID, max(COURSE_OFFERINGS.NAME), count(SECTIONS.NUMBER).as(sectionCount))
              .from(COURSES)
              .join(COURSE_OFFERINGS.on(COURSE_OFFERINGS.COURSE_UUID.eq(COURSES.UUID)))
              .join(SECTIONS.on(SECTIONS.COURSE_OFFERING_UUID.eq(COURSE_OFFERINGS.UUID)))
              .groupBy(COURSES.UUID)
              .having(sectionCount.greaterThan(1000));

      System.out.println(query.build());

      try (Cursor cursor = query.fetch()) {
        System.out.println("Largest Courses:");
        while (cursor.moveNext()) {
          System.out.println("\tCourse: " + cursor.getString(2).orElse("N/A"));
          System.out.println("\t\tSections: " + cursor.getNonNullNumber(3));
          System.out.println();
        }
      }
    }
  }
}
