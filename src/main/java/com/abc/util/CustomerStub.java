package com.abc.util;

import com.abc.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerStub {
    private static Map<Long, Customer> customers = new HashMap<>();
    private static Long idIndex = 0L;

    //populate initial customers list
    static {
        Customer c1  = new Customer(++idIndex, "Janardhan", "Hyd", PriorityType.PREMIUM, "B1");
        customers.put(idIndex,c1);
        Customer c2  = new Customer(++idIndex, "Raj", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c2);
        Customer c3  = new Customer(++idIndex, "R1", "Hyd", PriorityType.PREMIUM, "B1");
        customers.put(idIndex,c3);
        Customer c4  = new Customer(++idIndex, "J2", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c4);
        Customer c5  = new Customer(++idIndex, "Mahesh", "Hyd", PriorityType.PREMIUM, "B1");
        customers.put(idIndex,c5);
        Customer c6  = new Customer(++idIndex, "Rajan", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c6);
        Customer c7  = new Customer(++idIndex, "Judo", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c7);
        Customer c8  = new Customer(++idIndex, "Bidessy", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c8);
        Customer c9  = new Customer(++idIndex, "Emily", "Hyd", PriorityType.REGULAR, "B1");
        customers.put(idIndex,c9);
        Customer c10  = new Customer(++idIndex, "Elora", "Hyd", PriorityType.PREMIUM, "B1");
        customers.put(idIndex,c10);
    }

    public static List<Customer> list() {
        return new ArrayList<>(customers.values());
    }

    public static Customer create(Customer customer) {
        customer.setCustId(++   idIndex);
        customers.put(idIndex, customer);
        return customer;
    }

    public static Customer get(Long id) {
        return customers.get(id);
    }

    public static Customer update(Long id, Customer customer) {
        customers.put(id, customer);
        return customer;
    }

    public static Customer delete(Long id) {
        return customers.remove(id);
    }
}
