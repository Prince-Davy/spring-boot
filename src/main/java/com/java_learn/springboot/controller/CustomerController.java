package com.java_learn.springboot.controller;

import com.java_learn.springboot.CustomerRepository;
import com.java_learn.springboot.dao.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers/")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age) {
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customersId}")
    public void deleteCustomer(@PathVariable("customersId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customersId}")
    public void updateCustomer(@PathVariable("customersId") Integer id,
                               @RequestBody NewCustomerRequest request) {

        Optional<Customer> customer = Optional.of(customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Id " + id + " doesn't exist")));

        Customer customerExist = customer.get();
        customerExist.setName(request.name());
        customerExist.setEmail(request.email());
        customerExist.setAge(request.age());
        customerRepository.save(customerExist);
    }
}
