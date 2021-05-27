package com.denys.taxi.dto;


public class AutoDto {

  private int id;

  private String mark;

  private String autoNumber;


  public AutoDto(int id, String mark, String autoNumber) {
    this.id = id;
    this.mark = mark;
    this.autoNumber = autoNumber;

  }


  public AutoDto() {

  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getAutoNumber() {
    return autoNumber;
  }

  public void setAutoNumber(String autoNumber) {
    this.autoNumber = autoNumber;
  }
}