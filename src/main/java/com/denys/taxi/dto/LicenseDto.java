package com.denys.taxi.dto;


public class LicenseDto {

  private int id;

  private String dateOfIssue;


  public LicenseDto(int id, String dateOfIssue) {
    this.id = id;
    this.dateOfIssue = dateOfIssue;

  }


  public LicenseDto() {

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

}
