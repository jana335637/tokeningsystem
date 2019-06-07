package com.abc;

import com.abc.controller.CustomerController;
import com.abc.model.Customer;
import com.abc.util.PriorityType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for tokening system.
 */
public class AppTest {

    @Test
    public void testApp() {
        CustomerController cc = new CustomerController();
        cc.get(1l);
        assertNotNull(cc.get(1l));
        //assertEquals(((Customer)(cc.get(1l)).getBody()).getPriorityType(), PriorityType.REGULAR);
    }
}
