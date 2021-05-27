package com.denys.taxi.dto;

public class DriverDataDto {

  private int id;


  private String dateOfIssue;
  private String mark;
  private String autoNumber;


  public DriverDataDto(int id, String dateOfIssue, String mark, String autoNumber) {
    this.id = id;
    this.dateOfIssue = dateOfIssue;
    this.mark = mark;
    this.autoNumber = autoNumber;

  }


  public DriverDataDto() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDateOfIssue() {
    return dateOfIssue;
  }

  public void setDateOfIssue(String dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
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

