import com.keenant.flow.sql.Cursor;
import com.keenant.flow.sql.Result;
import com.keenant.flow.sql.SQLDatabase;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.ISQLDatabase;
import com.keenant.flow.sql.impl.ISelect;
import com.keenant.flow.sql.impl.exp.FieldExp;
import com.keenant.flow.sql.impl.exp.ParamExp;
import com.keenant.flow.sql.impl.filter.CompareFilter;
import com.keenant.flow.sql.impl.filter.CompareFilter.Comparator;
import com.keenant.flow.sql.jdbc.QueryConfig;
import com.keenant.flow.sql.jdbc.QueryMode;

import java.util.stream.Stream;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        SQLDatabase db = new ISQLDatabase();
        db.open();

        ISelect select = new ISelect(new FieldExp("users"));
        select.where(new CompareFilter(new FieldExp("name"),new ParamExp("Keenan"), Comparator.EQUALS));

        System.out.println(select.build(SQLDialect.SQLITE));

        QueryConfig config = QueryConfig.builder(QueryMode.FETCH).build();

        Result res = db.prepareQuery(select.build(SQLDialect.SQLITE), config).execute();

        Cursor cursor = res.safeEagerCursor();

        while (cursor.moveNext()) {
            System.out.println(cursor.get(1));
        }
    }
}
