package com.praveen.Spring_Insurance_Management_System.dto;

import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy; 

public class ClaimDto {

    private Long claimId;
    private long claimNumber;
    private String claimDescription;
    private String claimDate;
    private String claimStatus;
    private String claimAmount;
    private InsurancePolicy insurancePolicy; 

    public ClaimDto() {
        super();
    }

    public ClaimDto(Long claimId, long claimNumber, String claimDescription, String claimDate,
                    String claimStatus, String claimAmount, InsurancePolicy insurancePolicy) {
        this.claimId = claimId;
        this.claimNumber = claimNumber;
        this.claimDescription = claimDescription;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
        this.claimAmount = claimAmount;
        this.insurancePolicy = insurancePolicy;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public long getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(long claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
