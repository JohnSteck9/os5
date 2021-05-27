package com.pizza.controller;

import com.pizza.domain.CourierStatusEntity;
import com.pizza.dto.CourierStatusDTO;
import com.pizza.service.CourierStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/courier_status")
@RestController

public class CourierStatusController {
    private final CourierStatusService courierStatusService;

    public CourierStatusController(CourierStatusService courierStatusService){
        this.courierStatusService = courierStatusService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CourierStatusDTO>> getAll() {
        List<CourierStatusEntity> courierStatuses = courierStatusService.getAll();

        List<CourierStatusDTO> courierStatusDTOs = new ArrayList<>();
        for (CourierStatusEntity courierStatus : courierStatuses) {
            CourierStatusDTO courierStatusDTO = new CourierStatusDTO (
                    courierStatus.getId(),
                    courierStatus.getStatus()
            );
            courierStatusDTOs.add(courierStatusDTO);
        }
        return new ResponseEntity<>(courierStatusDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CourierStatusDTO> getById(@PathVariable Integer id) {
        CourierStatusEntity courierStatus = courierStatusService.getById(id);
        if (courierStatus != null) {
            CourierStatusDTO courierStatusDTO = new CourierStatusDTO(
                    courierStatus.getId(),
                    courierStatus.getStatus()
            );
            return new ResponseEntity<>(courierStatusDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody CourierStatusEntity newCourierStatus) {
        courierStatusService.create(newCourierStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CourierStatusDTO> update(@PathVariable Integer id,
                                             @RequestBody CourierStatusEntity courierStatus) {
        CourierStatusEntity oldCourierStatus = courierStatusService.getById(id);
        if (oldCourierStatus != null) {
            courierStatusService.update(id, courierStatus);
            CourierStatusDTO oldCourierStatusDTO = new CourierStatusDTO(
                    courierStatus.getId(),
                    courierStatus.getStatus()
            );
            return new ResponseEntity<>(oldCourierStatusDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (courierStatusService.getById(id) != null) {
            courierStatusService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
