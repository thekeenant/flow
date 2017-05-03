package com.keenant.flow.examples;

import com.keenant.flow.DatabaseContext;
import com.keenant.flow.SelectScoped;
import com.keenant.flow.impl.exp.Field;

import static com.keenant.flow.Flow.*;

public class UsersTable {
    private static final Field USERS = field("users");
    private static final Field ID = USERS.column("id");
    private static final Field NAME = USERS.column("name");
    private static final Field AGE = USERS.column("age");

    private DatabaseContext database;

    public UsersTable(DatabaseContext database) {
        this.database = database;
    }

    private SelectScoped select() {
        return database.selectFrom(USERS);
    }
    
    public int numElderly() {
        return select()
                .fields(count())
                .where(AGE.gte(75))
                .fetch()
                .first()
                .getNonNullNumber(1)
                .intValue();
    }

    public int oldestAge() {
        return select()
                .fields(max(AGE))
                .fetch()
                .first()
                .getNonNullInt(1);
    }

    public double averageNameLength() {
        return select()
                .fields(avg(length(NAME)))
                .fetch()
                .first()
                .getNonNullNumber(1)
                .doubleValue();
    }
}
