package com.pizza.controller;

import com.pizza.domain.CourierEntity;
import com.pizza.dto.CourierDTO;
import com.pizza.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/courier")
@RestController


public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService){
        this.courierService = courierService;
    }

    @RequestMapping(method = RequestMethod.GET)
    // зверніть увагу: get - віддає щось до користувача, то ми маємо віддати DTO, а не об`єкти
    public ResponseEntity<List<CourierDTO>> getAll() {
        // стягуємо всі ентіті
        List<CourierEntity> couriers = courierService.getAll();
        // створюємо (поки що) пустий список для DTOшок
        List<CourierDTO> courierDTOs = new ArrayList<>();
        // перебираємо кожен ентіті, що ми знайшли, конвертуємо в DTO і додаємо DTO до ліста
        for (CourierEntity courier : couriers) {
            CourierDTO courierDTO = new CourierDTO (
                    courier.getId(),    // сетаємо значення
                    courier.getFirstName(),
                    courier.getLastName(),
                    courier.getPhone(),
                    courier.getEmail(),
                    courier.getCourierStatusId()
            );
            courierDTOs.add(courierDTO);
        }
        return new ResponseEntity<>(courierDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    // віддаємо на клієнтську частину дані - знову ж таки, віддаємо їх як DTO
    public ResponseEntity<CourierDTO> getById(@PathVariable Integer id) {
        CourierEntity courier = courierService.getById(id);
        if (courier != null) {
            CourierDTO courierDTO = new CourierDTO(
                    courier.getId(),
                    courier.getFirstName(),
                    courier.getLastName(),
                    courier.getPhone(),
                    courier.getEmail(),
                    courier.getCourierStatusId()
            );
            return new ResponseEntity<>(courierDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // параметр consumes - кажемо, що POST в нас хаває дані теж в форматі JSON
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody CourierEntity newCourier) {
        // а тут DTOшок вже не буде, бо нам ж треба в базу зберегти нормальний об'єкт, а не DTO

        courierService.create(newCourier);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CourierDTO> update(@PathVariable Integer id,
                                           @RequestBody CourierEntity courier) {
        CourierEntity oldCourier = courierService.getById(id);
        if (oldCourier != null) {
            courierService.update(id, courier);
            CourierDTO oldLabelDTO = new CourierDTO(
                    courier.getId(),
                    courier.getFirstName(),
                    courier.getLastName(),
                    courier.getPhone(),
                    courier.getEmail(),
                    courier.getCourierStatusId()
            );
            return new ResponseEntity<>(oldLabelDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (courierService.getById(id) != null) {
            courierService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}