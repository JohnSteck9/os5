package com.denys.taxi.domain;


import javax.persistence.*;

@Table(name = "license")
@Entity
public class License {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "date_of_issue")

  private String dateOfIssue;
  @Column(name = "place_of_issue")
  private String placeOfIssue;

  public License(int id, String dateOfIssue, String placeOfIssue) {
    this.id = id;
    this.dateOfIssue = dateOfIssue;
    this.placeOfIssue = placeOfIssue;
  }

  public License(String dateOfIssue, String placeOfIssue) {
    this.dateOfIssue = dateOfIssue;
    this.placeOfIssue = placeOfIssue;
  }

  public License() {

  }

  @Override
  public String toString() {
    return "\n\nLicense: id: " + id + ", dateOfIssue: " + dateOfIssue + ", placeOfIssue: " + placeOfIssue + "";
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

  public String getPlaceOfIssue() {
    return placeOfIssue;
  }

  public void setPlaceOfIssue(String placeOfIssue) {
    this.placeOfIssue = placeOfIssue;
  }
}
