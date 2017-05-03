package com.keenant.flow;

public enum Comparator {
    EQUALS("="),
    NOT_EQUAL("<>"),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">=");

    private final String symbol;

    Comparator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}