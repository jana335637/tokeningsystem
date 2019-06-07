package com.abc.model;

import com.abc.util.PriorityType;

import java.util.Objects;

public class Customer {
    private Long custId;
    private String name;
    private String address;
    private PriorityType priorityType;
    private String branchId;

    public Customer(){}

    public Customer(Long custId, String name, String address, PriorityType priorityType, String branchId) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.priorityType = priorityType;
        this.branchId = branchId;
    }
    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(custId, customer.custId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId);
    }
}
