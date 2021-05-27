package com.pizza.service;

import com.pizza.domain.CourierStatusEntity;
import com.pizza.repository.CourierStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourierStatusService implements AbstractService<CourierStatusEntity, Integer> {
    private final CourierStatusRepository courierStatusRepository;

    public CourierStatusService(CourierStatusRepository courierStatusRepository){
        this.courierStatusRepository = courierStatusRepository;
    }

    @Override
    public List<CourierStatusEntity> getAll() {
        return courierStatusRepository.findAll();
    }

    @Override
    public CourierStatusEntity getById(Integer id) {
        return courierStatusRepository.getOne(id);
    }

    @Override
    public CourierStatusEntity create(CourierStatusEntity newObject) {
        return courierStatusRepository.save(newObject);
    }

    @Override
    public CourierStatusEntity update(Integer id, CourierStatusEntity object) {
        if (courierStatusRepository.findById(id).isPresent()){
            return courierStatusRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (courierStatusRepository.findById(id).isPresent()) {
            courierStatusRepository.deleteById(id);
        }
    }


}