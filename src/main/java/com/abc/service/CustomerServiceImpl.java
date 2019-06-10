package com.abc.service;

import com.abc.dao.CustomerDAO;
import com.abc.model.Customer;
import com.abc.util.PriorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> getCustomers() {
        return customerDAO.list();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerDAO.update(id, customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.create(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerDAO.delete(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.get(id);
    }

    @Override
    public PriorityType getCustomerServiceTypeById(Long id) {
        if (customerDAO.get(id) != null)
            return customerDAO.get(id).getPriorityType();
        return null;
    }
}
