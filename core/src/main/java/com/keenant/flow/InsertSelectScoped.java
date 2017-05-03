package com.keenant.flow;

public interface InsertSelectScoped extends InsertSelect {
    @Override
    InsertSelectScoped cpy();

    @Override
    InsertSelectScoped table(Exp table);

    QueryPart build();

    void execute();
}
