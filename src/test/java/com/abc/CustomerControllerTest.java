package com.abc;

import com.abc.controller.CustomerController;
import com.abc.controller.TokenController;
import com.abc.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController cc;

    @Test
    public void testTokenGet() {
        CustomerController cc = new CustomerController();

        Assert.assertEquals((long)((Customer)cc.get(1l).getBody()).getCustId(),1l);
    }
}
