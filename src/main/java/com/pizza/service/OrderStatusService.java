package com.pizza.service;

import com.pizza.domain.OrderStatusEntity;
import com.pizza.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService implements AbstractService<OrderStatusEntity, Integer>{
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatusEntity> getAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatusEntity getById(Integer id) {
        return orderStatusRepository.getOne(id);
    }

    @Override
    public OrderStatusEntity create(OrderStatusEntity newObject) {
        return orderStatusRepository.save(newObject);
    }

    @Override
    public OrderStatusEntity update(Integer id, OrderStatusEntity object) {
        if (orderStatusRepository.findById(id).isPresent()) {
            return orderStatusRepository.save(object);
        } else {
            return null;
        }

    }

    @Override
    public void deleteById(Integer id) {
        if (orderStatusRepository.findById(id).isPresent()) {
            orderStatusRepository.deleteById(id);
        }

    }

}