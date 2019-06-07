package com.abc.service;

import com.abc.model.Customer;
import com.abc.util.PriorityType;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    Customer updateCustomer(Long id, Customer customer);
    Customer createCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    PriorityType getCustomerServiceTypeById(Long id);
}
