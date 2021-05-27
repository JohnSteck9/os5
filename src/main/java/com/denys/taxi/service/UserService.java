package com.denys.taxi.service;

import com.denys.taxi.domain.User;
import com.denys.taxi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements AbstractService<User, Integer> {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User getById(Integer id) {
    return userRepository.getOne(id);
  }

  @Override
  public User create(User newObject) {
    return userRepository.save(newObject);
  }

  @Override
  public User update(Integer id, User object) {
    if (userRepository.findById(id).isPresent()) {
      return userRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    }
  }
}