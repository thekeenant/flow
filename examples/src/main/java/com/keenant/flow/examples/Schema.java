package com.keenant.flow.examples;

import static com.keenant.flow.Flow.column;

import com.keenant.flow.Column;
import com.keenant.flow.exp.FieldExp;

public class Schema {
  public static Courses COURSES = new Courses();
  public static CourseOfferings COURSE_OFFERINGS = new CourseOfferings();
  public static Sections SECTIONS = new Sections();

  public static class Courses extends FieldExp {
    public final Column<String> UUID = column(this, "uuid");
    public final Column<Integer> NUMBER = column(this, "number");

    private Courses() {
      super("courses");
    }
  }

  public static class CourseOfferings extends FieldExp {
    public final Column<String> UUID = column(this, "uuid");
    public final Column<String> COURSE_UUID = column(this, "course_uuid");
    public final Column<String> NAME = column(this, "name");
    public final Column<Integer> TERM_CODE = column(this, "term_code");

    private CourseOfferings() {
      super("course_offerings");
    }
  }

  public static class Sections extends FieldExp {
    public final Column<Integer> NUMBER = column(this, "number");
    public final Column<String> COURSE_OFFERING_UUID = column(this, "course_offering_uuid");

    private Sections() {
      super("sections");
    }
  }
}
