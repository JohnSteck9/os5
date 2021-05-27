package com.denys.taxi.controller;

import com.denys.taxi.domain.License;
import com.denys.taxi.dto.LicenseDto;
import com.denys.taxi.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/license")
@RestController

public class LicenseController {
  private final LicenseService licenseService;

  public LicenseController(LicenseService licenseService) {
    this.licenseService = licenseService;
  }

  @RequestMapping(method = RequestMethod.GET)

  public ResponseEntity<List<LicenseDto>> getAll() {

    List<License> licenses = licenseService.getAll();

    List<LicenseDto> licenseDtos = new ArrayList<>();

    for (License license : licenses) {
      LicenseDto licenseDto = new LicenseDto(
          license.getId(),
          license.getDateOfIssue()

      );
      licenseDtos.add(licenseDto);
    }
    return new ResponseEntity<>(licenseDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")

  public ResponseEntity<LicenseDto> getById(@PathVariable Integer id) {
    License license = licenseService.getById(id);
    try {

      LicenseDto licenseDto = new LicenseDto(
          license.getId(),
          license.getDateOfIssue()

      );
      return new ResponseEntity<>(licenseDto, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody License newLicense) {

    licenseService.create(newLicense);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<LicenseDto> update(@PathVariable Integer id,
                                           @RequestBody License license) {
    License oldLicense = licenseService.getById(id);

    if (oldLicense != null && oldLicense.getDateOfIssue() != null) {
      licenseService.update(id, license);
      LicenseDto oldLicenseDto = new LicenseDto(
          license.getId(),
          license.getDateOfIssue()
      );
      return new ResponseEntity<>(oldLicenseDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

    try {
      if (licenseService.getById(id) != null && licenseService.getById(id).getDateOfIssue() != null) {
        licenseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}