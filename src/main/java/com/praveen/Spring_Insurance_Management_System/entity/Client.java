package com.praveen.Spring_Insurance_Management_System.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="client_tb")
public class Client {    // owning side - child
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="client_id")
	private Long clientId;
	
	@Column(name="client_name")
	private String clientName;
	
	@Column(name="client_date_of_birth")
	private String clientDateOfBirth;
	
	@Column(name="client_address")
	private String clientAddress;
	
	@Column(name="client_contact_information")
	private String clientContactInformation;
	
	@ManyToOne
	@JoinColumn(name="insurance_policy_id")
	private InsurancePolicy insurancePolicy;

	public Client() {
		super();
	}

	public Client(Long clientId, String clientName, String clientDateOfBirth, String clientAddress,
			String clientContactInformation, InsurancePolicy insurancePolicy) {
		super();
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
