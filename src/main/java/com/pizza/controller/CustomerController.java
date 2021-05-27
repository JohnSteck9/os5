package com.pizza.controller;

import com.pizza.domain.CustomerEntity;
import com.pizza.dto.CustomerDTO;
import com.pizza.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/customer")
@RestController

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerEntity> customers = customerService.getAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (CustomerEntity customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO (
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone(),
                    customer.getEmail()
            );
            customerDTOs.add(customerDTO);
        }
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Integer id) {
        CustomerEntity customer = customerService.getById(id);
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone(),
                    customer.getEmail()
            );
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody CustomerEntity newCustomer) {


        customerService.create(newCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id,
                                                  @RequestBody CustomerEntity customer) {
        CustomerEntity oldCustomer = customerService.getById(id);
        if (oldCustomer != null) {
            customerService.update(id, customer);
            CustomerDTO oldCustomerDTO = new CustomerDTO(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone(),
                    customer.getEmail()
            );
            return new ResponseEntity<>(oldCustomerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (customerService.getById(id) != null) {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}