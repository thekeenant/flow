package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

public class DeleteScoped implements QueryPartBuilder {
    private final Delete delete;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    private DeleteScoped(Delete delete, DatabaseContext database, SQLDialect dialect) {
        this.delete = delete;
        this.database = database;
        this.dialect = dialect;
    }

    public DeleteScoped(Exp table, DatabaseContext database, SQLDialect dialect) {
        this(new Delete(table), database, dialect);
    }

    public DatabaseContext getDatabase() {
        return database;
    }

    public SQLDialect getDialect() {
        return dialect;
    }

    public QueryPart build() {
        return delete.build(dialect);
    }

    public EagerCursor fetch() {
        return delete.fetch(database, dialect);
    }

    public Cursor fetchLazy() throws DatabaseException {
        return delete.fetchLazy(database, dialect);
    }

    public DeleteScoped cpy() {
        return new DeleteScoped(delete.cpy(), database, dialect);
    }

    public DeleteScoped table(Exp table) {
        delete.table(table);
        return this;
    }


    public DeleteScoped where(Filter filter) {
        delete.where(filter);
        return this;
    }

    public EagerCursor fetch(DatabaseContext database, SQLDialect dialect) {
        return delete.fetch(database, dialect);
    }

    public Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) throws DatabaseException {
        return delete.fetchLazy(database, dialect);
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return delete.build(dialect);
    }
}
