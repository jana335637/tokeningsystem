package com.abc.dao;

import com.abc.model.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> list();

    public Customer create(Customer customer);

    public Customer get(Long id);

    public Customer update(Long id, Customer customer);


    public Customer delete(Long id);
}