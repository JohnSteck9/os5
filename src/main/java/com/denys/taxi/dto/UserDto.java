package com.denys.taxi.dto;


public class UserDto {

  private int id;

  private String firstName;
  private String secondName;

  private Float rating;
  private String dateOfIssue;


  private String mark;
  private String autoNumber;

  public UserDto(int id, String firstName, String secondName, Float rating, String dateOfIssue, String mark, String autoNumber) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
    this.rating = rating;
    this.dateOfIssue = dateOfIssue;
    this.mark = mark;
    this.autoNumber = autoNumber;

  }

  public UserDto(int id, String firstName, String secondName, Float rating) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
    this.rating = rating;

  }

  public UserDto() {

  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
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
