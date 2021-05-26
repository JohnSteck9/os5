package com.pizza.service;

import com.pizza.domain.DeliveryAreaEntity;
import com.pizza.repository.DeliveryAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryAreaService implements AbstractService<DeliveryAreaEntity, Integer> {
    private final DeliveryAreaRepository deliveryAreaRepository;

    public DeliveryAreaService(DeliveryAreaRepository deliveryAreaRepository){
        this.deliveryAreaRepository = deliveryAreaRepository;
    }

    @Override
    public List<DeliveryAreaEntity> getAll() {
        return deliveryAreaRepository.findAll();
    }

    @Override
    public DeliveryAreaEntity getById(Integer id) {
        return deliveryAreaRepository.getOne(id);
    }

    @Override
    public DeliveryAreaEntity create(DeliveryAreaEntity newObject) {
        return deliveryAreaRepository.save(newObject);
    }

    @Override
    public DeliveryAreaEntity update(Integer id, DeliveryAreaEntity object) {
        if (deliveryAreaRepository.findById(id).isPresent()){
            return deliveryAreaRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (deliveryAreaRepository.findById(id).isPresent()) {
            deliveryAreaRepository.deleteById(id);
        }
    }


}