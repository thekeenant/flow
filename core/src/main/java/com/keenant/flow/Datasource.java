package com.keenant.flow;

import com.keenant.flow.exception.DatasourceException;

public interface Datasource {
    void open() throws DatasourceException;

    void close() throws DatasourceException;
}
