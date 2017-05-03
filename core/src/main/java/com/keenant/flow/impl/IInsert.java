package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.ParamExp;
import com.keenant.flow.jdbc.QueryConfig;
import com.keenant.flow.jdbc.QueryMode;

import java.util.*;

public class IInsert implements Insert {
    private final Exp table;
    private final List<Map<String, Exp>> records;

    public IInsert(Exp table) {
        this.table = table;
        records = new ArrayList<>();
    }

    private Map<String, Exp> currentRecord() {
        return records.get(records.size() - 1);
    }

    private Set<String> columnNames() {
        Set<String> names = new HashSet<>();
        for (Map<String, Exp> record : records) {
            names.addAll(record.keySet());
        }
        return names;
    }

    @Override
    public Insert cpy() {
        IInsert insert = new IInsert(table);
        for (Map<String, Exp> record : records) {
            insert.records.add(new HashMap<>(record));
        }
        return insert;
    }

    @Override
    public Insert with(String field, Exp value) {
        currentRecord().put(field, value);
        return this;
    }

    @Override
    public Insert with(String field, Object value) {
        return with(field, new ParamExp(value));
    }

    @Override
    public Insert newRecord() {
        records.add(new HashMap<>());
        return this;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        Set<String> columns = columnNames();

        sql.append("INSERT INTO ");

        {
            QueryPart tablePart = table.build(dialect);
            sql.append(tablePart.getSql());
            params.addAll(tablePart.getParams());
        }

        sql.append(" (");
        for (String column : columns) {
            sql.append(column);
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES ");

        for (Map<String, Exp> record : records) {
            sql.append("(");
            for (String column : columns) {
                QueryPart curr = record.get(column).build(dialect);
                sql.append(curr.getSql());
                params.addAll(curr.getParams());
                sql.append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append("),");
        }
        sql.deleteCharAt(sql.length() - 1);

        return new IQueryPart(sql.toString(), params);
    }

    @Override
    public void execute(SQLDatabase database, SQLDialect dialect) {
        QueryPart part = build(dialect);
        QueryConfig config = QueryConfig.builder(QueryMode.UPDATE).build();

        Query query = database.prepareQuery(part, config);
        query.execute();
    }
}
