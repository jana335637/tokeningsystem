package com.abc.model;

public class Branch {
    private String branchId;
    private String numberOfCounters;
    private String phoneNumber;

    public Branch(){}

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNumberOfCounters() {
        return numberOfCounters;
    }

    public void setNumberOfCounters(String numberOfCounters) {
        this.numberOfCounters = numberOfCounters;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
