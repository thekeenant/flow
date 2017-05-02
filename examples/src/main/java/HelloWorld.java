import com.keenant.flow.sql.*;
import com.keenant.flow.sql.impl.ISQLDatabase;
import com.keenant.flow.sql.impl.ISelect;
import com.keenant.flow.sql.impl.exp.*;
import com.keenant.flow.sql.impl.filter.AndFilter;
import com.keenant.flow.sql.impl.filter.CompareFilter;
import com.keenant.flow.sql.impl.filter.CompareFilter.Comparator;
import com.keenant.flow.sql.jdbc.QueryConfig;
import com.keenant.flow.sql.jdbc.QueryMode;

import java.util.Collections;
import java.util.stream.Stream;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        SQLDatabase db = new ISQLDatabase();
        db.open();

        ISelect select = new ISelect(new FieldExp("users"));
        select.where(new CompareFilter(new FieldExp("name"),new ParamExp("Keenan"), Comparator.EQUALS));

        System.out.println(select.build(SQLDialect.SQLITE));

        try (Stream<Cursor> cursor = select.fetch(db, SQLDialect.MYSQL).stream()) {
            int sum = cursor.mapToInt(record -> record.getInt(1).orElse(0)).sum();
            System.out.println(sum);
        }

    }
}
