package com.keenant.flow;

public abstract class AbstractUnaryFilter extends AbstractFilter {
    private final Filter child;

    public AbstractUnaryFilter(Filter child) {
        this.child = child;
    }

    protected abstract String getSqlFormat(SQLDialect dialect);

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart part = child.build(dialect);

        String sql = String.format(getSqlFormat(dialect), part.getSql());

        return new QueryPart(sql, part.getParams());
    }
}
