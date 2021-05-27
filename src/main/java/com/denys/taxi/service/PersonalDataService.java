package com.denys.taxi.service;

import com.denys.taxi.domain.PersonalData;
import com.denys.taxi.repository.PersonalDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PersonalDataService implements AbstractService<PersonalData, Integer> {
  private final PersonalDataRepository personalDataRepository;

  public PersonalDataService(PersonalDataRepository personalDataRepository) {
    this.personalDataRepository = personalDataRepository;
  }


  @Override
  public List<PersonalData> getAll() {
    return personalDataRepository.findAll();
  }

  @Override
  public PersonalData getById(Integer id) {
    return personalDataRepository.getOne(id);
  }

  @Override
  public PersonalData create(PersonalData newObject) {
    return personalDataRepository.save(newObject);
  }

  @Override
  public PersonalData update(Integer id, PersonalData object) {
    if (personalDataRepository.findById(id).isPresent()) {
      return personalDataRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (personalDataRepository.findById(id).isPresent()) {
      personalDataRepository.deleteById(id);
    }
  }
}