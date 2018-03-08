package com.keenant.flow;

import com.keenant.flow.exp.functions.ListExp;

public class SelectPrefix {
  private ListExp fields;

  public SelectPrefix(ListExp fields) {
    this.fields = fields;
  }

  public Select from(Exp table) {
    return new Select(table, fields);
  }
}
