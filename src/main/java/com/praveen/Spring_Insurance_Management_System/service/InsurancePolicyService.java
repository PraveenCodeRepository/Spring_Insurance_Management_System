package com.praveen.Spring_Insurance_Management_System.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.Spring_Insurance_Management_System.dto.InsurancePolicyDto;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.mapper.InsurancePolicyMapper;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyService {
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	public InsurancePolicyDto createInsurancePolicy(InsurancePolicyDto insurancePolicyDto) {
	    InsurancePolicy insurancePolicy = InsurancePolicyMapper.toInsurancePolicyEntity(insurancePolicyDto);
	    
	    InsurancePolicy insurancePolicyCreated = insurancePolicyRepository.save(insurancePolicy);
	    
	    return InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyCreated);
	}
	public Set<InsurancePolicyDto> getAllPolicies() {
		
		  List<InsurancePolicy> insurancePolicyList = insurancePolicyRepository.findAll();
		  
		  Set<InsurancePolicy> insurancePolicySet = new HashSet<>(insurancePolicyList);
		  
	     return InsurancePolicyMapper.toInsurancePolicyDtoSet(insurancePolicySet);
		  
	
	}

	public InsurancePolicyDto getPolicyByPolicyId(Long policyId) {
	   
	InsurancePolicy insurancePolicy =	insurancePolicyRepository.findById(policyId)
		                         .orElseThrow(()-> new InsurancePolicyNotFoundException(
		                        		 "Insurance Policy not found with id :"+policyId));
	 return   InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicy);
		
	}

	public InsurancePolicyDto updatePolicyByPolicyId(Long policyId, InsurancePolicyDto insurancePolicyDto) {
	    InsurancePolicy existInsurancePolicy = insurancePolicyRepository.findById(policyId)
	            .orElseThrow(() -> new InsurancePolicyNotFoundException(
	                    "Insurance Policy not found with id: " + policyId));

	    if (insurancePolicyDto.getInsurancePolicyNumber() != null) {
	        existInsurancePolicy.setInsurancePolicyNumber(insurancePolicyDto.getInsurancePolicyNumber());
	    }
	    if (insurancePolicyDto.getInsurancePolicyCoverageAmount() != 0) {
	        existInsurancePolicy.setInsurancePolicyCoverageAmount(insurancePolicyDto.getInsurancePolicyCoverageAmount());
	    }
	    if (insurancePolicyDto.getInsurancePolicyPremium() != null) {
	        existInsurancePolicy.setInsurancePolicyPremium(insurancePolicyDto.getInsurancePolicyPremium());
	    }
	    if (insurancePolicyDto.getInsurancePolicyType() != null) {
	        existInsurancePolicy.setInsurancePolicyType(insurancePolicyDto.getInsurancePolicyType());
	    }
	    if (insurancePolicyDto.getInsurancePolicyStartDate() != null) {
	        existInsurancePolicy.setInsurancePolicyStartDate(insurancePolicyDto.getInsurancePolicyStartDate());
	    }
	    if (insurancePolicyDto.getInsurancePolicyEndDate() != null) {
	        existInsurancePolicy.setInsurancePolicyEndDate(insurancePolicyDto.getInsurancePolicyEndDate());
	    }

	    InsurancePolicy updatedInsurancePolicy = insurancePolicyRepository.save(existInsurancePolicy);

	    return InsurancePolicyMapper.toInsurancePolicyDto(updatedInsurancePolicy);
	}

	public void deletePolicyByPolicyId(Long policyId) {
		
		InsurancePolicy deleteInsurancePolicy =	insurancePolicyRepository.findById(policyId)
                .orElseThrow(()-> new InsurancePolicyNotFoundException(
               		 "Insurance Policy not found with id :"+policyId));
		insurancePolicyRepository.delete(deleteInsurancePolicy);
		
	}


}
