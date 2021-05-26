package com.pizza.controller;

import com.pizza.domain.OrderStatusEntity;
import com.pizza.dto.OrderStatusDTO;
import com.pizza.service.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/order_status")
@RestController

public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService){
        this.orderStatusService = orderStatusService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderStatusDTO>> getAll() {
        List<OrderStatusEntity> orderStatuses = orderStatusService.getAll();

        List<OrderStatusDTO> orderStatusDTOs = new ArrayList<>();
        for (OrderStatusEntity orderStatus : orderStatuses) {
            OrderStatusDTO orderStatusDTO = new OrderStatusDTO (
                    orderStatus.getId(),
                    orderStatus.getStatus()
            );
            orderStatusDTOs.add(orderStatusDTO);
        }
        return new ResponseEntity<>(orderStatusDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<OrderStatusDTO> getById(@PathVariable Integer id) {
        OrderStatusEntity orderStatus = orderStatusService.getById(id);
        if (orderStatus != null) {
            OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
                    orderStatus.getId(),
                    orderStatus.getStatus()
            );
            return new ResponseEntity<>(orderStatusDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody OrderStatusEntity newOrderStatus) {
        orderStatusService.create(newOrderStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderStatusDTO> update(@PathVariable Integer id,
                                             @RequestBody OrderStatusEntity orderStatus) {
        OrderStatusEntity oldOrderStatus = orderStatusService.getById(id);
        if (oldOrderStatus != null) {
            orderStatusService.update(id, orderStatus);
            OrderStatusDTO oldOrderStatusDTO = new OrderStatusDTO(
                    orderStatus.getId(),
                    orderStatus.getStatus()
            );
            return new ResponseEntity<>(oldOrderStatusDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (orderStatusService.getById(id) != null) {
            orderStatusService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
