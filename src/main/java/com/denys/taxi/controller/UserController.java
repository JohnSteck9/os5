package com.denys.taxi.controller;

import com.denys.taxi.domain.User;
import com.denys.taxi.dto.UserDto;
import com.denys.taxi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/user")
@RestController

public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)

  public ResponseEntity<List<UserDto>> getAll() {

    List<User> users = userService.getAll();

    List<UserDto> userDtos = new ArrayList<>();



    for (User user : users) {


      try {
        UserDto userDto = new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getSecondName(),
            user.getPersonalData().getRating(),
            user.getPersonalData().getDriverData().getLicense().getDateOfIssue(),
            user.getPersonalData().getDriverData().getAuto().getMark(),
            user.getPersonalData().getDriverData().getAuto().getAutoNumber()
        );
        userDtos.add(userDto);
      } catch (NullPointerException e) {
        UserDto userDto = new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getSecondName(),
            user.getPersonalData().getRating());
        userDtos.add(userDto);
      }
    }
    return new ResponseEntity<>(userDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Integer id) {
    User user = userService.getById(id);
    try {

      UserDto userDto = new UserDto(
          user.getId(),
          user.getFirstName(),
          user.getSecondName(),
          user.getPersonalData().getRating(),
          user.getPersonalData().getDriverData().getLicense().getDateOfIssue(),
          user.getPersonalData().getDriverData().getAuto().getMark(),
          user.getPersonalData().getDriverData().getAuto().getAutoNumber()

      );
      return new ResponseEntity<>(userDto, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (NullPointerException e) {
      UserDto userDto = new UserDto(
          user.getId(),
          user.getFirstName(),
          user.getSecondName(),
          user.getPersonalData().getRating());
      return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


  }

  // параметр consumes - кажемо, що POST в нас хаває дані теж в форматі JSON
  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody User newUser) {
    // а тут DTOшок вже не буде, бо нам ж треба в базу зберегти нормальний об'єкт,
    // а не DTO, правда?
    userService.create(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDto> update(@PathVariable Integer id,
                                        @RequestBody User user) {
    User oldUser = userService.getById(id);

    if (oldUser != null && oldUser.getPersonalData().getRating() != null) {

      userService.update(id, user);
      try {
        UserDto oldUserDto = new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getSecondName(),
            user.getPersonalData().getRating(),
            user.getPersonalData().getDriverData().getLicense().getDateOfIssue(),
            user.getPersonalData().getDriverData().getAuto().getMark(),
            user.getPersonalData().getDriverData().getAuto().getAutoNumber()
        );
        return new ResponseEntity<>(oldUserDto, HttpStatus.OK);
      } catch (NullPointerException e) {
        UserDto oldUserDto = new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getSecondName(),
            user.getPersonalData().getRating()
        );
        return new ResponseEntity<>(oldUserDto, HttpStatus.OK);
      }

    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

    try {
      if (userService.getById(id) != null && userService.getById(id).getPersonalData().getRating() != null) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}