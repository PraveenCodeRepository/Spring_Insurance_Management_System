package com.praveen.Spring_Insurance_Management_System.dto;

public class InsurancePolicyDto {
    
    private Long insurancePolicyId;
    private String insurancePolicyNumber;
    private String insurancePolicyType;
    private long insurancePolicyCoverageAmount;
    private String insurancePolicyPremium;
    private String insurancePolicyStartDate;
    private String insurancePolicyEndDate;

    public InsurancePolicyDto() {
    }

    public InsurancePolicyDto(Long insurancePolicyId, String insurancePolicyNumber, String insurancePolicyType, 
                              long insurancePolicyCoverageAmount, String insurancePolicyPremium, 
                              String insurancePolicyStartDate, String insurancePolicyEndDate) {
        this.insurancePolicyId = insurancePolicyId;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.insurancePolicyType = insurancePolicyType;
        this.insurancePolicyCoverageAmount = insurancePolicyCoverageAmount;
        this.insurancePolicyPremium = insurancePolicyPremium;
        this.insurancePolicyStartDate = insurancePolicyStartDate;
        this.insurancePolicyEndDate = insurancePolicyEndDate;
    }

    public Long getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public void setInsurancePolicyId(Long insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getInsurancePolicyType() {
        return insurancePolicyType;
    }

    public void setInsurancePolicyType(String insurancePolicyType) {
        this.insurancePolicyType = insurancePolicyType;
    }

    public long getInsurancePolicyCoverageAmount() {
        return insurancePolicyCoverageAmount;
    }

    public void setInsurancePolicyCoverageAmount(long insurancePolicyCoverageAmount) {
        this.insurancePolicyCoverageAmount = insurancePolicyCoverageAmount;
    }

    public String getInsurancePolicyPremium() {
        return insurancePolicyPremium;
    }

    public void setInsurancePolicyPremium(String insurancePolicyPremium) {
        this.insurancePolicyPremium = insurancePolicyPremium;
    }

    public String getInsurancePolicyStartDate() {
        return insurancePolicyStartDate;
    }

    public void setInsurancePolicyStartDate(String insurancePolicyStartDate) {
        this.insurancePolicyStartDate = insurancePolicyStartDate;
    }

    public String getInsurancePolicyEndDate() {
        return insurancePolicyEndDate;
    }

    public void setInsurancePolicyEndDate(String insurancePolicyEndDate) {
        this.insurancePolicyEndDate = insurancePolicyEndDate;
    }
}
