package com.pizza.service;

import com.pizza.domain.CustomerEntity;
import com.pizza.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService implements AbstractService<CustomerEntity, Integer> {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getById(Integer id) {
        return customerRepository.getOne(id);
    }

    @Override
    public CustomerEntity create(CustomerEntity newObject) {
        return customerRepository.save(newObject);
    }

    @Override
    public CustomerEntity update(Integer id, CustomerEntity object) {
        if (customerRepository.findById(id).isPresent()){
            return customerRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        }
    }


}