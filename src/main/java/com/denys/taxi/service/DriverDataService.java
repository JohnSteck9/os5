package com.denys.taxi.service;


import com.denys.taxi.domain.DriverData;
import com.denys.taxi.repository.DriverDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverDataService implements AbstractService<DriverData, Integer> {
  private final DriverDataRepository driverDataRepository;

  public DriverDataService(DriverDataRepository driverDataRepository) {
    this.driverDataRepository = driverDataRepository;
  }


  @Override
  public List<DriverData> getAll() {
    return driverDataRepository.findAll();
  }

  @Override
  public DriverData getById(Integer id) {
    return driverDataRepository.getOne(id);
  }

  @Override
  public DriverData create(DriverData newObject) {
    return driverDataRepository.save(newObject);
  }

  @Override
  public DriverData update(Integer id, DriverData object) {
    if (driverDataRepository.findById(id).isPresent()) {
      return driverDataRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (driverDataRepository.findById(id).isPresent()) {
      driverDataRepository.deleteById(id);
    }
  }
}