package com.pizza.service;

import com.pizza.domain.OrderInfoEntity;
import com.pizza.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService implements AbstractService<OrderInfoEntity, Integer>{
    private final OrderInfoRepository orderInfoRepository;

    public OrderInfoService(OrderInfoRepository orderInfoRepository){
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    public List<OrderInfoEntity> getAll() {
        return orderInfoRepository.findAll();
    }

    @Override
    public OrderInfoEntity getById(Integer id) {
        return orderInfoRepository.getOne(id);
    }

    @Override
    public OrderInfoEntity create(OrderInfoEntity newObject) {
        return orderInfoRepository.save(newObject);
    }

    @Override
    public OrderInfoEntity update(Integer id, OrderInfoEntity object) {
        if (orderInfoRepository.findById(id).isPresent()) {
            return orderInfoRepository.save(object);
        } else {
            return null;
        }

    }

    @Override
    public void deleteById(Integer id) {
        if (orderInfoRepository.findById(id).isPresent()) {
            orderInfoRepository.deleteById(id);
        }

    }

}