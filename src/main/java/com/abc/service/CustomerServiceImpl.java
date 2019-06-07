package com.abc.service;

import com.abc.model.Customer;
import com.abc.util.CustomerStub;
import com.abc.util.PriorityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> getCustomers() {
        return CustomerStub.list();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return CustomerStub.update(id, customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return CustomerStub.create(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return CustomerStub.delete(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return CustomerStub.get(id);
    }

    @Override
    public PriorityType getCustomerServiceTypeById(Long id) {
        if (CustomerStub.get(id) != null)
            return CustomerStub.get(id).getPriorityType();
        return null;
    }
}
