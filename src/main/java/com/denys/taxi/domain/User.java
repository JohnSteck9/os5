package com.denys.taxi.domain;


import javax.persistence.*;

@Table(name = "user")
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "second_name")
  private String secondName;
  @Column(name = "gender")
  private String gender;

  @OneToOne
  @JoinColumn(name = "personal_data_id", referencedColumnName = "id", nullable = false)
  private PersonalData personalData;

  public User(int id, String firstName, String secondName, String gender, PersonalData personalData) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
    this.gender = gender;

    this.personalData = personalData;

  }

  public User(String firstName, String secondName, String gender, PersonalData personalData) {
    this.firstName = firstName;
    this.secondName = secondName;
    this.gender = gender;

    this.personalData = personalData;
  }


  public User() {

  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  @Override
  public String toString() {
    return "\n______________________________________________________________________________________________________________________\n" +
        "Passenger: id: " + id + ", firstName: " + firstName + ", secondName: " + secondName + ", gender: "
        + gender + ", personalDataId: " + personalData
        + "]\n______________________________________________________________________________________________________________________\n";
  }


}
