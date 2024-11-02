package com.praveen.Spring_Insurance_Management_System.dto;

import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy; 

public class ClientDto {  // owning side - child

    private Long clientId;
    private String clientName;
    private String clientDateOfBirth;
    private String clientAddress;
    private String clientContactInformation;
    private InsurancePolicy insurancePolicy; 

    public ClientDto() {
        super();
    }

    public ClientDto(Long clientId, String clientName, String clientDateOfBirth, String clientAddress,
                     String clientContactInformation, InsurancePolicy insurancePolicy) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientDateOfBirth = clientDateOfBirth;
        this.clientAddress = clientAddress;
        this.clientContactInformation = clientContactInformation;
        this.insurancePolicy = insurancePolicy;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientDateOfBirth() {
        return clientDateOfBirth;
    }

    public void setClientDateOfBirth(String clientDateOfBirth) {
        this.clientDateOfBirth = clientDateOfBirth;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientContactInformation() {
        return clientContactInformation;
    }

    public void setClientContactInformation(String clientContactInformation) {
        this.clientContactInformation = clientContactInformation;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
