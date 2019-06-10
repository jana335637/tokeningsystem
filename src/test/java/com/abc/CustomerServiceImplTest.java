package com.abc;

import com.abc.dao.CustomerDAOImpl;
import com.abc.model.Customer;
import com.abc.service.CustomerServiceImpl;
import com.abc.util.PriorityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    CustomerDAOImpl customerDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer(1l, "Rahim", "Bang", PriorityType.PREMIUM, "B1");
        when(customerDAO.get(1l)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.getCustomerById(1l))).getCustId(), 1l);
    }


    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1l, "Rahim", "Bang", PriorityType.PREMIUM, "B1");
        when(customerDAO.create(customer)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.createCustomer(customer))).getCustId(), 1l);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer(1l, "Rahim", "Bang", PriorityType.PREMIUM, "B1");
        when(customerDAO.update(1l, customer)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.updateCustomer(1l, customer))).getCustId(), 1l);
    }

    @Test
    public void testGetTokenPriority() {
        Customer customer = new Customer(1l, "Rahim", "Bang", PriorityType.PREMIUM, "B1");
        when(customerDAO.delete(1l)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.deleteCustomer(1l))).getCustId(), 1l);
    }


}
