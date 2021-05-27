package com.denys.taxi.controller;

import com.denys.taxi.domain.DriverData;
import com.denys.taxi.dto.DriverDataDto;
import com.denys.taxi.service.DriverDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/driverData")
@RestController

public class DriverDataController {
  private final DriverDataService driverDataService;

  public DriverDataController(DriverDataService driverDataService) {
    this.driverDataService = driverDataService;
  }

  @RequestMapping(method = RequestMethod.GET)

  public ResponseEntity<List<DriverDataDto>> getAll() {

    List<DriverData> driverDatas = driverDataService.getAll();
    List<DriverDataDto> driverDataDtos = new ArrayList<>();
    for (DriverData driverData : driverDatas) {
      DriverDataDto driverDataDto = new DriverDataDto(
          driverData.getId(),
          driverData.getLicense().getDateOfIssue(),
          driverData.getAuto().getMark(),
          driverData.getAuto().getAutoNumber()

      );
      driverDataDtos.add(driverDataDto);
    }
    return new ResponseEntity<>(driverDataDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<DriverDataDto> getById(@PathVariable Integer id) {
    DriverData driverData = driverDataService.getById(id);
    try {

      DriverDataDto driverDataDto = new DriverDataDto(
          driverData.getId(),
          driverData.getLicense().getDateOfIssue(),
          driverData.getAuto().getMark(),
          driverData.getAuto().getAutoNumber()

      );
      return new ResponseEntity<>(driverDataDto, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


  }


  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody DriverData newDriverData) {

    driverDataService.create(newDriverData);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<DriverDataDto> update(@PathVariable Integer id,
                                              @RequestBody DriverData driverData) {
    DriverData oldDriverData = driverDataService.getById(id);

    if (oldDriverData != null && oldDriverData.getLicense().getDateOfIssue() != null && oldDriverData.getAuto().getMark() != null) {
      driverDataService.update(id, driverData);
      DriverDataDto oldDriverDataDto = new DriverDataDto(
          driverData.getId(),
          driverData.getLicense().getDateOfIssue(),
          driverData.getAuto().getMark(),
          driverData.getAuto().getAutoNumber()

      );
      return new ResponseEntity<>(oldDriverDataDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

    try {
      if (driverDataService.getById(id) != null && driverDataService.getById(id).getAuto().getMark() != null && driverDataService.getById(id).getLicense().getDateOfIssue() != null) {
        driverDataService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
