package com.praveen.Spring_Insurance_Management_System.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.praveen.Spring_Insurance_Management_System.dto.InsurancePolicyDto;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;

@Component
public class InsurancePolicyMapper {
	
	
	public static InsurancePolicyDto toInsurancePolicyDto(InsurancePolicy insurancePolicy) {
		
		 if(insurancePolicy==null) return null;
		 
		  InsurancePolicyDto insurancePolicyDto = new InsurancePolicyDto();
		  
		  insurancePolicyDto.setInsurancePolicyId(insurancePolicy.getInsurancePolicyId());
		  insurancePolicyDto.setInsurancePolicyNumber(insurancePolicy.getInsurancePolicyNumber());
		  insurancePolicyDto.setInsurancePolicyCoverageAmount(insurancePolicy.getInsurancePolicyCoverageAmount());
		  insurancePolicyDto.setInsurancePolicyPremium(insurancePolicy.getInsurancePolicyPremium());
		  insurancePolicyDto.setInsurancePolicyType(insurancePolicy.getInsurancePolicyType());
		  insurancePolicyDto.setInsurancePolicyStartDate(insurancePolicy.getInsurancePolicyStartDate());
		  insurancePolicyDto.setInsurancePolicyEndDate(insurancePolicy.getInsurancePolicyEndDate());
		  
		  return insurancePolicyDto;
	}
	
	public static InsurancePolicy toInsurancePolicyEntity(InsurancePolicyDto insurancePolicyDto) {
		 
		  if(insurancePolicyDto==null) return null;
		
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		
		insurancePolicy.setInsurancePolicyId(insurancePolicyDto.getInsurancePolicyId());
		insurancePolicy.setInsurancePolicyPremium(insurancePolicyDto.getInsurancePolicyPremium());
		insurancePolicy.setInsurancePolicyNumber(insurancePolicyDto.getInsurancePolicyNumber());
		insurancePolicy.setInsurancePolicyCoverageAmount(insurancePolicyDto.getInsurancePolicyCoverageAmount());
		insurancePolicy.setInsurancePolicyType(insurancePolicyDto.getInsurancePolicyType());
		insurancePolicy.setInsurancePolicyStartDate(insurancePolicyDto.getInsurancePolicyStartDate());
		insurancePolicy.setInsurancePolicyEndDate(insurancePolicyDto.getInsurancePolicyEndDate());
		
		return insurancePolicy;
	}
	
	public static Set<InsurancePolicy> toInsurancePolicyEntitySet(Set<InsurancePolicyDto> insurancePolicyDtos){
		  
		     if(insurancePolicyDtos==null) return null;
		     
		     Set<InsurancePolicy> insurancePolicies = new HashSet<>();
		     
		        if(insurancePolicies!=null)
		              for(InsurancePolicyDto insurancePolicyDto :insurancePolicyDtos) 
		    	             insurancePolicies.add(InsurancePolicyMapper.toInsurancePolicyEntity(insurancePolicyDto));
		                       return insurancePolicies;
	}
	public static Set<InsurancePolicyDto> toInsurancePolicyDtoSet(Set<InsurancePolicy> insurancePolicies){
		  
	     if(insurancePolicies==null) return null;
	     
	     Set<InsurancePolicyDto> insurancePolicyDtos = new HashSet<>();
	     
	        if(insurancePolicies!=null)
	              for(InsurancePolicy insurancePolicy :insurancePolicies) 
	    	             insurancePolicyDtos.add(InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicy));
	                       return insurancePolicyDtos;
}

}
