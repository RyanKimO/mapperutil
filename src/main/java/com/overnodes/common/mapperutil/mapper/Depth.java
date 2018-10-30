package com.overnodes.common.mapperutil.mapper;

import java.util.List;

public class Depth {

  private Double price;
  private Double quantity;
  private List<String> ignore;

  public Depth() {
  }

  public Depth(Double price, Double quantity, List<String> ignore) {
    this.price = price;
    this.quantity = quantity;
    this.ignore = ignore;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public List<String> getIgnore() {
    return ignore;
  }

  public void setIgnore(List<String> ignore) {
    this.ignore = ignore;
  }
}