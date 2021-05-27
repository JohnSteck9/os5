package com.denys.taxi.service;

import com.denys.taxi.domain.License;
import com.denys.taxi.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService implements AbstractService<License, Integer> {
  private final LicenseRepository licenseRepository;

  public LicenseService(LicenseRepository licenseRepository) {
    this.licenseRepository = licenseRepository;
  }


  @Override
  public List<License> getAll() {
    return licenseRepository.findAll();
  }

  @Override
  public License getById(Integer id) {
    return licenseRepository.getOne(id);
  }

  @Override
  public License create(License newObject) {
    return licenseRepository.save(newObject);
  }

  @Override
  public License update(Integer id, License object) {
    if (licenseRepository.findById(id).isPresent()) {
      return licenseRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (licenseRepository.findById(id).isPresent()) {
      licenseRepository.deleteById(id);
    }
  }
}