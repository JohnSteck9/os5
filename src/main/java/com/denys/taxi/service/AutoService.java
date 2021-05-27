package com.denys.taxi.service;

import com.denys.taxi.domain.Auto;
import com.denys.taxi.repository.AutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService implements AbstractService<Auto, Integer> {
  private final AutoRepository autoRepository;

  public AutoService(AutoRepository autoRepository) {
    this.autoRepository = autoRepository;
  }


  @Override
  public List<Auto> getAll() {
    return autoRepository.findAll();
  }

  @Override
  public Auto getById(Integer id) {
    return autoRepository.getOne(id);
  }

  @Override
  public Auto create(Auto newObject) {
    return autoRepository.save(newObject);
  }

  @Override
  public Auto update(Integer id, Auto object) {
    if (autoRepository.findById(id).isPresent()) {
      return autoRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (autoRepository.findById(id).isPresent()) {
      autoRepository.deleteById(id);
    }
  }
}