package com.gamo.ecommerce1.controller;

import com.gamo.ecommerce1.model.Customers;
import com.gamo.ecommerce1.DAO.CRUDCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customers")
public class CustomersController {
    @Autowired
    private final CRUDCustomers customersRepository;

    public CustomersController(CRUDCustomers customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customers getCustomerById(@PathVariable int id) {
        return customersRepository.findById(id);
    }

    @PostMapping
    public Customers createCustomer(@RequestBody Customers customers) {
        return customersRepository.insert(customers);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody Customers customers) {
        customers.setId(id);
        customersRepository.update(customers);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable int id) {
        return customersRepository.delete(id);
    }
}
