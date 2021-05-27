package com.denys.taxi.domain;


import javax.persistence.*;


@Table(name = "auto")
@Entity
public class Auto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "mark")
  private String mark;
  @Column(name = "auto_number")
  private String autoNumber;
  @Column(name = "auto_type")
  private String autoType;
  @Column(name = "state")
  private String state;

  public Auto(int id, String mark, String autoNumber, String autoType, String state) {
    this.id = id;
    this.mark = mark;
    this.autoNumber = autoNumber;
    this.autoType = autoType;
    this.state = state;
  }

  public Auto(String mark, String autoNumber, String autoType, String state) {
    this.mark = mark;
    this.autoNumber = autoNumber;
    this.autoType = autoType;
    this.state = state;
  }

  public Auto() {

  }

  @Override
  public String toString() {
    return "\n\nAuto: id: " + id + ", mark: " + mark + ", autoNumber: " + autoNumber + ", autoType: " + autoType + ", state: " + state + "";
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

  public String getAutoType() {
    return autoType;
  }

  public void setAutoType(String autoType) {
    this.autoType = autoType;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
