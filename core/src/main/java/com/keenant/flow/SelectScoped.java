package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.util.Collection;

/**
 * A select query which is targeted for a specific database with a specific dialect.
 */
public interface SelectScoped extends Select {
    DatabaseContext getDatabase();

    SQLDialect getDialect();

    QueryPart build();

    EagerCursor fetch() throws DatabaseException;

    Cursor fetchLazy() throws DatabaseException;

    // Override Select

    @Override
    SelectScoped cpy();

    @Override
    SelectScoped table(Exp table);

    @Override
    SelectScoped fields(Collection<Exp> fields);

    @Override
    SelectScoped fields(Exp... fields);

    @Override
    SelectScoped where(Filter filter);

    @Override
    SelectScoped order(Exp order);
}
