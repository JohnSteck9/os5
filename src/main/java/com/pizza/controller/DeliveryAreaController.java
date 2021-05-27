package com.pizza.controller;

import com.pizza.domain.DeliveryAreaEntity;
import com.pizza.dto.DeliveryAreaDTO;
import com.pizza.service.DeliveryAreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/delivery_area")
@RestController

public class DeliveryAreaController {

    private final DeliveryAreaService deliveryAreaService;

    public DeliveryAreaController(DeliveryAreaService deliveryAreaService){
        this.deliveryAreaService = deliveryAreaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DeliveryAreaDTO>> getAll() {
        List<DeliveryAreaEntity> deliveryAreas = deliveryAreaService.getAll();
        List<DeliveryAreaDTO> deliveryAreaDTOs = new ArrayList<>();
        for (DeliveryAreaEntity deliveryArea : deliveryAreas) {
            DeliveryAreaDTO deliveryAreaDTO = new DeliveryAreaDTO (
                    deliveryArea.getId(),
                    deliveryArea.getZone(),
                    deliveryArea.getDeliveryTime()
            );
            deliveryAreaDTOs.add(deliveryAreaDTO);
        }
        return new ResponseEntity<>(deliveryAreaDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<DeliveryAreaDTO> getById(@PathVariable Integer id) {
        DeliveryAreaEntity deliveryArea = deliveryAreaService.getById(id);
        if (deliveryArea != null) {
            DeliveryAreaDTO deliveryAreaDTO = new DeliveryAreaDTO(
                    deliveryArea.getId(),
                    deliveryArea.getZone(),
                    deliveryArea.getDeliveryTime()
            );
            return new ResponseEntity<>(deliveryAreaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody DeliveryAreaEntity newDeliveryArea) {


        deliveryAreaService.create(newDeliveryArea);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DeliveryAreaDTO> update(@PathVariable Integer id,
                                                  @RequestBody DeliveryAreaEntity deliveryArea) {
        DeliveryAreaEntity oldDeliveryArea = deliveryAreaService.getById(id);
        if (oldDeliveryArea != null) {
            deliveryAreaService.update(id, deliveryArea);
            DeliveryAreaDTO oldDeliveryAreaDTO = new DeliveryAreaDTO(
                    deliveryArea.getId(),
                    deliveryArea.getZone(),
                    deliveryArea.getDeliveryTime()
            );
            return new ResponseEntity<>(oldDeliveryAreaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (deliveryAreaService.getById(id) != null) {
            deliveryAreaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}