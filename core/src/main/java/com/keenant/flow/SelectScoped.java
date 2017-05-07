package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.util.Arrays;
import java.util.Collection;

/**
 * A select query which is targeted for a specific database with a specific dialect.
 */
public interface SelectScoped extends Select {
    /**
     * @return the database associated with this query
     */
    DatabaseContext getDatabase();

    /**
     * @return the dialect associated with this query
     */
    SQLDialect getDialect();

    /**
     * See {@link Select#build(SQLDialect)}.
     */
    QueryPart build();

    /**
     * See {@link Select#fetch(DatabaseContext, SQLDialect)}.
     */
    EagerCursor fetch() throws DatabaseException;

    /**
     * See {@link Select#fetchLazy(DatabaseContext, SQLDialect)}.
     */
    Cursor fetchLazy() throws DatabaseException;

    // we override Select, modifying the returned object to an instance of SelectScoped

    @Override
    SelectScoped cpy();

    @Override
    SelectScoped distinct(boolean distinct);

    @Override
    SelectScoped table(Exp table);

    @Override
    SelectScoped fields(Collection<Exp> fields);

    @Override
    default SelectScoped fields(Exp... fields) {
        return fields(Arrays.asList(fields));
    }

    @Override
    SelectScoped where(Filter filter);

    @Override
    SelectScoped order(Exp order);
}
