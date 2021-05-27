package com.denys.taxi.controller;


import com.denys.taxi.domain.Auto;
import com.denys.taxi.dto.AutoDto;
import com.denys.taxi.service.AutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/auto")
@RestController

public class AutoController {
  private final AutoService autoService;

  public AutoController(AutoService autoService) {
    this.autoService = autoService;
  }

  @RequestMapping(method = RequestMethod.GET)
  // зверніть увагу: get - віддає щось до користувача, то ми маємо віддати DTO, а не об'єкти
  public ResponseEntity<List<AutoDto>> getAll() {
    // стягуємо всі ентіті
    List<Auto> autos = autoService.getAll();
    // створюємо (поки що) пустий список для DTOшок
    List<AutoDto> autoDtos = new ArrayList<>();
    // перебираємо кожен ентіті, що ми знайшли, конвертуємо в DTO і додаємо DTO до ліста
    for (Auto auto : autos) {
      AutoDto autoDto = new AutoDto(
          auto.getId(),
          auto.getMark(),
          auto.getAutoNumber()

      );
      autoDtos.add(autoDto);
    }
    return new ResponseEntity<>(autoDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  // віддаємо на клієнтську частину дані - знову ж таки, віддаємо їх як DTO
  public ResponseEntity<AutoDto> getById(@PathVariable Integer id) {
    Auto auto = autoService.getById(id);
    try {

      // і знову та сама хуйня дублюється... бачите чому класно мати якийсь Mapper?
      AutoDto autoDto = new AutoDto(
          auto.getId(),
          auto.getMark(),
          auto.getAutoNumber()

      );
      return new ResponseEntity<>(autoDto, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


  }

  // параметр consumes - кажемо, що POST в нас хаває дані теж в форматі JSON
  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Auto newAuto) {
    // а тут DTOшок вже не буде, бо нам ж треба в базу зберегти нормальний об'єкт,
    // а не DTO, правда?
    autoService.create(newAuto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<AutoDto> update(@PathVariable Integer id,
                                        @RequestBody Auto auto) {
    Auto oldAuto = autoService.getById(id);

    if (oldAuto != null && oldAuto.getMark() != null) {
      autoService.update(id, auto);
      AutoDto oldAutoDto = new AutoDto(
          auto.getId(),
          auto.getMark(),
          auto.getAutoNumber()
      );
      return new ResponseEntity<>(oldAutoDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

    try {
      if (autoService.getById(id) != null && autoService.getById(id).getMark() != null) {
        autoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
