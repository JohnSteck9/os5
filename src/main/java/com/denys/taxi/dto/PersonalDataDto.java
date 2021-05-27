package com.denys.taxi.dto;

public class PersonalDataDto {

  private int id;
  private Float rating;
  private String dateOfIssue;
  private String mark;
  private String autoNumber;


  public PersonalDataDto(int id, Float rating, String dateOfIssue, String mark, String autoNumber) {
    this.id = id;
    this.rating = rating;
    this.dateOfIssue = dateOfIssue;
    this.mark = mark;
    this.autoNumber = autoNumber;
  }

  public PersonalDataDto(int id, Float rating) {
    this.id = id;
    this.rating = rating;

  }

  public PersonalDataDto() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public String getDateOfIssue() {

    return dateOfIssue;
  }

  public void setDateOfIssue(String dateOfIssue) {

    this.dateOfIssue = dateOfIssue;
  }

  public String getMark() {
    if (mark == null) {
      mark = "empty";
    }
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getAutoNumber() {
    if (autoNumber == null) {
      autoNumber = "empty";
    }

    return autoNumber;
  }

  public void setAutoNumber(String autoNumber) {
    this.autoNumber = autoNumber;
  }


}
