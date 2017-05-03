package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.exception.DatabaseException;

import java.util.Collection;

public class ISelectScoped implements SelectScoped {
    private final Select select;
    private final SQLDatabase database;
    private final SQLDialect dialect;

    private ISelectScoped(Select select, SQLDatabase database, SQLDialect dialect) {
        this.select = select;
        this.database = database;
        this.dialect = dialect;
    }

    public ISelectScoped(Exp table, SQLDatabase database, SQLDialect dialect) {
        this(new ISelect(table), database, dialect);
    }

    @Override
    public SQLDatabase getDatabase() {
        return database;
    }

    @Override
    public SQLDialect getDialect() {
        return dialect;
    }

    @Override
    public QueryPart build() {
        return select.build(dialect);
    }

    @Override
    public EagerCursor fetch() {
        return select.fetch(database, dialect);
    }

    @Override
    public Cursor fetchLazy() throws DatabaseException {
        return select.fetchLazy(database, dialect);
    }

    @Override
    public SelectScoped cpy() {
        return new ISelectScoped(select.cpy(), database, dialect);
    }

    @Override
    public SelectScoped table(Exp table) {
        select.table(table);
        return this;
    }

    @Override
    public SelectScoped fields(Collection<Exp> fields) {
        select.fields(fields);
        return this;
    }

    @Override
    public SelectScoped fields(Exp... fields) {
        select.fields(fields);
        return this;
    }

    @Override
    public SelectScoped where(Filter filter) {
        select.where(filter);
        return this;
    }

    @Override
    public SelectScoped order(Exp order) {
        select.order(order);
        return this;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return select.build(dialect);
    }

    @Override
    public EagerCursor fetch(SQLDatabase database, SQLDialect dialect) {
        return select.fetch(database, dialect);
    }

    @Override
    public Cursor fetchLazy(SQLDatabase database, SQLDialect dialect) throws DatabaseException {
        return select.fetchLazy(database, dialect);
    }
}
