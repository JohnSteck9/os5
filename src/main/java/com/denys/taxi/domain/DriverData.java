package com.denys.taxi.domain;

import javax.persistence.*;

@Table(name = "driver_data")
@Entity
public class DriverData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "driving_experience")
  private Float drivingExperience;


  @OneToOne
  @JoinColumn(name = "license_id", referencedColumnName = "id", nullable = false)
  private License license;


  @OneToOne
  @JoinColumn(name = "auto_id", referencedColumnName = "id", nullable = false)
  private Auto auto;

  @Column(name = "is_booked")
  private Boolean isBooked;

  public DriverData(int id, Float drivingExperience, License license, Auto auto, Boolean isBooked) {
    this.id = id;
    this.drivingExperience = drivingExperience;
    this.license = license;
    this.auto = auto;
    this.isBooked = isBooked;
  }


  public DriverData(Float drivingExperience, License license, Auto auto, Boolean isBooked) {
    this.drivingExperience = drivingExperience;
    this.license = license;
    this.auto = auto;
    this.isBooked = isBooked;
  }

  public DriverData() {

  }

  @Override
  public String toString() {
    return "\n______________________________________________________________________________________________________________________\nDriverTaxiInfo: id: " + id + ", drivingExperience: " + drivingExperience + ", licenseId: "
        + license + ", autoId: " + auto + ", isBooked: " + isBooked
        + "]\n______________________________________________________________________________________________________________________\n";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Float getDrivingExperience() {
    return drivingExperience;
  }

  public void setDrivingExperience(Float drivingExperience) {
    this.drivingExperience = drivingExperience;
  }


  public Boolean getBooked() {
    return isBooked;
  }

  public void setBooked(Boolean booked) {
    isBooked = booked;
  }

  public Auto getAuto() {
    return auto;
  }

  public void setAuto(Auto auto) {
    this.auto = auto;
  }

  public License getLicense() {
    return license;
  }

  public void setLicense(License license) {
    this.license = license;
  }
}

