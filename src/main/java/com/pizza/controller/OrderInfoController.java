package com.pizza.controller;

import com.pizza.domain.OrderInfoEntity;
import com.pizza.dto.OrderInfoDTO;
import com.pizza.service.OrderInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/order_info")
@RestController

public class OrderInfoController {
    private final OrderInfoService orderInfoService;

    public OrderInfoController(OrderInfoService orderInfoService){
        this.orderInfoService = orderInfoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderInfoDTO>> getAll() {
        List<OrderInfoEntity> orderInfos = orderInfoService.getAll();

        List<OrderInfoDTO> orderInfoDTOs = new ArrayList<>();
        for (OrderInfoEntity orderInfo : orderInfos) {
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO (
                    orderInfo.getId(),
                    orderInfo.getComment(),
                    orderInfo.getPriceProduct(),
                    orderInfo.getPriceDelivery(),
                    orderInfo.getExpectedTime(),
                    orderInfo.getActualTime(),
                    orderInfo.getDeliveryAreaId(),
                    orderInfo.getOrderStatusId(),
                    orderInfo.getCustomerId(),
                    orderInfo.getCourierId()
            );
            orderInfoDTOs.add(orderInfoDTO);
        }
        return new ResponseEntity<>(orderInfoDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<OrderInfoDTO> getById(@PathVariable Integer id) {
        OrderInfoEntity orderInfo = orderInfoService.getById(id);
        if (orderInfo != null) {
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO(
                    orderInfo.getId(),
                    orderInfo.getComment(),
                    orderInfo.getPriceProduct(),
                    orderInfo.getPriceDelivery(),
                    orderInfo.getExpectedTime(),
                    orderInfo.getActualTime(),
                    orderInfo.getDeliveryAreaId(),
                    orderInfo.getOrderStatusId(),
                    orderInfo.getCustomerId(),
                    orderInfo.getCourierId()
            );
            return new ResponseEntity<>(orderInfoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody OrderInfoEntity newOrderInfo) {
        orderInfoService.create(newOrderInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderInfoDTO> update(@PathVariable Integer id,
                                                   @RequestBody OrderInfoEntity orderInfo) {
        OrderInfoEntity oldOrderInfo = orderInfoService.getById(id);
        if (oldOrderInfo != null) {
            orderInfoService.update(id, orderInfo);
            OrderInfoDTO oldOrderInfoDTO = new OrderInfoDTO(
                    orderInfo.getId(),
                    orderInfo.getComment(),
                    orderInfo.getPriceProduct(),
                    orderInfo.getPriceDelivery(),
                    orderInfo.getExpectedTime(),
                    orderInfo.getActualTime(),
                    orderInfo.getDeliveryAreaId(),
                    orderInfo.getOrderStatusId(),
                    orderInfo.getCustomerId(),
                    orderInfo.getCourierId()
            );
            return new ResponseEntity<>(oldOrderInfoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (orderInfoService.getById(id) != null) {
            orderInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
