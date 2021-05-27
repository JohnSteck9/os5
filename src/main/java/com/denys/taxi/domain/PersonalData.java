package com.denys.taxi.domain;

import javax.persistence.*;

@Table(name = "personal_data")
@Entity

public class PersonalData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "date_of_birth")
  private String dateOfBirth;
  @Column(name = "date_of_registration")
  private String dateOfRegistration;
  @Column(name = "user_password")
  private String userPassword;

  @Column(name = "rating")
  private Float rating;

  @OneToOne
  @JoinColumn(name = "driver_data_id", referencedColumnName = "id", nullable = true)
  private DriverData driverData;


  public PersonalData(int id, String dateOfBirth, String dateOfRegistration, String userPassword, Float rating, DriverData driverData) {
    this.id = id;
    this.dateOfBirth = dateOfBirth;
    this.dateOfRegistration = dateOfRegistration;
    this.userPassword = userPassword;
    this.rating = rating;
    this.driverData = driverData;
  }

  public PersonalData(String dateOfBirth, String dateOfRegistration, String userPassword, Float rating, DriverData driverData) {
    this.dateOfRegistration = dateOfRegistration;
    this.dateOfBirth = dateOfBirth;
    this.userPassword = userPassword;
    this.rating = rating;
    this.driverData = driverData;
  }

  public PersonalData() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDateOfRegistration() {
    return dateOfRegistration;
  }

  public void setDateOfRegistration(String dateOfRegistration) {
    this.dateOfRegistration = dateOfRegistration;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public DriverData getDriverData() {


    return driverData;
  }

  public void setDriverData(DriverData driverData) {
    this.driverData = driverData;
  }

  @Override
  public String toString() {
    return "\n\nPassengerTaxiInfo: id: " + id + ", dateOfBirth: " + dateOfBirth + ", dateOfRegistration: " + dateOfRegistration + ", userPassword: " + userPassword + ", rating: " + rating + "DriverData" + driverData + "";
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}

