package com.keenant.flow;

import com.keenant.flow.impl.*;
import com.keenant.flow.impl.exp.*;

import java.util.Arrays;
import java.util.Collection;

public class Flow {
    private static final Exp WILDCARD = new InlineExp("*");

    public static DefaultConnector connector(String url) {
        return new DefaultConnector(url);
    }

    public static DatabaseContext database(SQLDialect dialect, Connector connector) {
        return new IDatabaseContext(dialect, connector);
    }

    public static DatabaseContext database(SQLDialect dialect, String url) {
        return database(dialect, connector(url));
    }

    public static Select selectFrom(Exp table) {
        return new ISelect(table);
    }

    public static Insert insertInto(Exp table) {
        return new IInsert(table);
    }

    // Todo: So many functions...

    public static <T> Column<T> column(Field table, String name, Class<T> type) {
        return new IColumn<>(table, name, type);
    }

    public static Exp wildcard() {
        return WILDCARD;
    }

    public static AbsExp abs(Exp exp) {
        return new AbsExp(exp);
    }

    public static AvgExp avg(Exp exp) {
        return new AvgExp(exp);
    }

    public static CountExp count() {
        return new CountExp(wildcard());
    }

    public static CountExp count(Exp exp) {
        return new CountExp(exp);
    }

    public static Field field(String field) {
        return new Field(field);
    }

    public static Field field(String table, String column) {
        return new Field(table, column);
    }

    public static Field field(Field table, String column) {
        return new Field(table, column);
    }

    public static InlineExp inline(String sql) {
        return new InlineExp(sql);
    }

    public static LengthExp length(Exp exp) {
        return new LengthExp(exp);
    }

    public static LCaseExp lcase(Exp exp) {
        return new LCaseExp(exp);
    }

    public static ListExp list(Collection<Exp> exps) {
        return new ListExp(exps);
    }

    public static ListExp list(Exp... exps) {
        return new ListExp(Arrays.asList(exps));
    }

    public static MaxExp max(Exp exp) {
        return new MaxExp(exp);
    }

    public static MinExp min(Exp exp) {
        return new MinExp(exp);
    }

    public static OrderExp order(Exp exp, Order order) {
        return new OrderExp(exp, order);
    }

    public static OrderExp orderAsc(Exp exp) {
        return new OrderExp(exp, Order.ASC);
    }

    public static OrderExp orderDesc(Exp exp) {
        return new OrderExp(exp, Order.DESC);
    }

    public static ParamExp param(Object object) {
        return new ParamExp(object);
    }

    public static SumExp sum(Exp exp) {
        return new SumExp(exp);
    }

    public static UCaseExp ucase(Exp exp) {
        return new UCaseExp(exp);
    }
}
