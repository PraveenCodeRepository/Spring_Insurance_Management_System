package com.praveen.Spring_Insurance_Management_System.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="insurance_policy_tb")
public class InsurancePolicy {
	
	@Id
	@Column(name="insurance_policy_id")
	private Long insurancePolicyId;
	
	@Column(name="insurance_policy_number")
	private String insurancePolicyNumber;
	
	@Column(name="insurance_policy_type")
	private String insurancePolicyType;
	
	@Column(name="insurance_policy_coverage_amount")
	private long insurancePolicyCoverageAmount;
	
	@Column(name="insurance_policy_premium")
	private String insurancePolicyPremium;
	
	@Column(name="insurance_policy_start_date")
	private String insurancePolicyStartDate;
	
	@Column(name="insurance_policy_end_date")
	private String insurancePolicyEndDate;

	public InsurancePolicy() {
		super();
	}

	public InsurancePolicy(Long insurancePolicyId, String insurancePolicyNumber, String insurancePolicyType,
			long insurancePolicyCoverageAmount, String insurancePolicyPremium, String insurancePolicyStartDate,
			String insurancePolicyEndDate) {
		super();
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
