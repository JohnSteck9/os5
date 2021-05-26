package com.pizza.service;

import com.pizza.domain.CourierEntity;
import com.pizza.repository.CourierRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourierService implements AbstractService<CourierEntity, Integer> {
    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository){
        this.courierRepository = courierRepository;
    }

    @Override
    public List<CourierEntity> getAll() {
        return courierRepository.findAll();
    }

    @Override
    public CourierEntity getById(Integer id) {
        return courierRepository.getOne(id);
    }

    @Override
    public CourierEntity create(CourierEntity newObject) {
        return courierRepository.save(newObject);
    }

    @Override
    public CourierEntity update(Integer id, CourierEntity object) {
        if (courierRepository.findById(id).isPresent()){
            return courierRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (courierRepository.findById(id).isPresent()) {
            courierRepository.deleteById(id);
        }
    }


}