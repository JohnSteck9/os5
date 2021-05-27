package com.denys.taxi.controller;

import com.denys.taxi.domain.PersonalData;
import com.denys.taxi.dto.PersonalDataDto;
import com.denys.taxi.service.PersonalDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/personalData")
@RestController

public class PersonalDataController {
  private final PersonalDataService personalDataService;

  public PersonalDataController(PersonalDataService personalDataService) {
    this.personalDataService = personalDataService;
  }

  @RequestMapping(method = RequestMethod.GET)

  public ResponseEntity<List<PersonalDataDto>> getAll() {

    List<PersonalData> personalDatas = personalDataService.getAll();

    List<PersonalDataDto> personalDataDtos = new ArrayList<>();


    for (PersonalData personalData : personalDatas) {


      try {
        PersonalDataDto personalDataDto = new PersonalDataDto(
            personalData.getId(),
            personalData.getRating(),
            personalData.getDriverData().getLicense().getDateOfIssue(),
            personalData.getDriverData().getAuto().getMark(),
            personalData.getDriverData().getAuto().getAutoNumber());
        personalDataDtos.add(personalDataDto);
      } catch (NullPointerException e) {
        PersonalDataDto personalDataDto = new PersonalDataDto(
            personalData.getId(),
            personalData.getRating());
        personalDataDtos.add(personalDataDto);
      }
    }
    return new ResponseEntity<>(personalDataDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")

  public ResponseEntity<PersonalDataDto> getById(@PathVariable Integer id) {
    PersonalData personalData = personalDataService.getById(id);
    try {


      PersonalDataDto personalDataDto = new PersonalDataDto(
          personalData.getId(),
          personalData.getRating(),
          personalData.getDriverData().getLicense().getDateOfIssue(),
          personalData.getDriverData().getAuto().getMark(),
          personalData.getDriverData().getAuto().getAutoNumber()

      );
      return new ResponseEntity<>(personalDataDto, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (NullPointerException e) {
      PersonalDataDto personalDataDto = new PersonalDataDto(
          personalData.getId(),
          personalData.getRating());
      return new ResponseEntity<>(personalDataDto, HttpStatus.OK);
    }


  }


  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody PersonalData newPersonalData) {

    personalDataService.create(newPersonalData);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<PersonalDataDto> update(@PathVariable Integer id,
                                                @RequestBody PersonalData personalData) {
    PersonalData oldPersonalData = personalDataService.getById(id);

    if (oldPersonalData != null && oldPersonalData.getRating() != null) {

      personalDataService.update(id, personalData);
      try {
        PersonalDataDto oldPersonalDataDto = new PersonalDataDto(
            personalData.getId(),
            personalData.getRating(),
            personalData.getDriverData().getLicense().getDateOfIssue(),
            personalData.getDriverData().getAuto().getMark(),
            personalData.getDriverData().getAuto().getAutoNumber()
        );
        return new ResponseEntity<>(oldPersonalDataDto, HttpStatus.OK);
      } catch (NullPointerException e) {
        PersonalDataDto oldPersonalDataDto = new PersonalDataDto(
            personalData.getId(),
            personalData.getRating()
        );
        return new ResponseEntity<>(oldPersonalDataDto, HttpStatus.OK);
      }

    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

    try {
      if (personalDataService.getById(id) != null && personalDataService.getById(id).getRating() != null) {
        personalDataService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
