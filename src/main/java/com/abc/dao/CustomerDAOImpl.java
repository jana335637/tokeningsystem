package com.abc.dao;

import com.abc.model.Customer;
import com.abc.util.CustomerStub;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    public List<Customer> list() {
        return CustomerStub.list();
    }

    public Customer create(Customer customer) {
        return CustomerStub.create(customer);
    }

    public Customer get(Long id) {
        return CustomerStub.get(id);
    }

    public Customer update(Long id, Customer customer) {
        return CustomerStub.update(id, customer);
    }

    public Customer delete(Long id) {
        return CustomerStub.delete(id);
    }
}
