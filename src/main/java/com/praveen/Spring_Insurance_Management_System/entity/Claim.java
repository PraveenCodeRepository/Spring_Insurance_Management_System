package com.praveen.Spring_Insurance_Management_System.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="claim_tb")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="claim_id")
	private Long claimId;
	
	@Column(name="claim_number")
	private long claimNumber;
	
	@Column(name="claim_description")
	private String claimDescription;
	
	@Column(name="claim_date")
	private String claimDate;
	
	@Column(name="claim_status")
	private String claimStatus;
	
	@Column(name="claim_amount")
	private String claimAmount;
	
	@OneToOne
	@JoinColumn(name="insurance_policy_id")
	private InsurancePolicy insurancePolicy;

	public Claim() {
		super();
	}

	public Claim(Long claimId, long claimNumber, String claimDescription, String claimDate, String claimStatus,
			String claimAmount, InsurancePolicy insurancePolicy) {
		super();
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
