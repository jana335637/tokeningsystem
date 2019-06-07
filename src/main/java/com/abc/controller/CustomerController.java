package com.abc.controller;

import com.abc.model.Customer;
import com.abc.service.CustomerService;
import com.abc.util.PriorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/abc/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (ObjectUtils.isEmpty(customer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/{id}/Type", method = RequestMethod.GET)
    public ResponseEntity getCustomerPriorityType(@PathVariable Long id) {
        try {
            PriorityType priorityType = customerService.getCustomerServiceTypeById(id);
            if (ObjectUtils.isEmpty(priorityType))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(priorityType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Customer customer) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity get(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            if (ObjectUtils.isEmpty(updatedCustomer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            Customer customer = customerService.deleteCustomer(id);
            if (ObjectUtils.isEmpty(customer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

}
