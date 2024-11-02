package com.praveen.Spring_Insurance_Management_System.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.praveen.Spring_Insurance_Management_System.dto.ClaimDto;
import com.praveen.Spring_Insurance_Management_System.entity.Claim;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.ClaimNotFoundException;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.mapper.ClaimMapper;
import com.praveen.Spring_Insurance_Management_System.repository.ClaimRepository;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

@Service
public class ClaimService {
    
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;
	

	public ClaimDto createClaim(ClaimDto claimDto) {
	    
	    if (claimDto.getInsurancePolicy() != null && claimDto.getInsurancePolicy().getInsurancePolicyId() != null) {
	        InsurancePolicy existingPolicy = insurancePolicyRepository.findById(claimDto.getInsurancePolicy().getInsurancePolicyId())
	                .orElseThrow(() -> new InsurancePolicyNotFoundException("Insurance Policy not found with id: " + claimDto.getInsurancePolicy().getInsurancePolicyId()));
	        
	        
	        claimDto.setInsurancePolicy(existingPolicy);
	    }

	   
	    Claim newClaim = new Claim();
	    newClaim.setClaimNumber(claimDto.getClaimNumber());
	    newClaim.setClaimDescription(claimDto.getClaimDescription());
	    newClaim.setClaimDate(claimDto.getClaimDate());
	    newClaim.setClaimStatus(claimDto.getClaimStatus());
	    newClaim.setClaimAmount(claimDto.getClaimAmount());
	    
	   
	    if (claimDto.getInsurancePolicy() != null) {
	        newClaim.setInsurancePolicy(claimDto.getInsurancePolicy());
	    }

	   
	    Claim savedClaim = claimRepository.save(newClaim);
	    
	    return ClaimMapper.toClaimDto(savedClaim);
	}

	public Set<ClaimDto> getAllClaimsByPolicyId(Long policyId) {
		
		  if(insurancePolicyRepository.existsById(policyId)) {
			 List<Claim> claimList = claimRepository.findAll();
			 Set<Claim> claimSet = new HashSet(claimList);
			return ClaimMapper.toClaimDtoSet(claimSet);
		  }else {
			  throw new InsurancePolicyNotFoundException("Policy is not found with these "+policyId+" policy id");
		  }
	}

	public ClaimDto getClaimByClaimId(Long claimId) {
		 
		 Claim claimFound = claimRepository.findById(claimId)
		                  .orElseThrow(()-> new ClaimNotFoundException("Claim not found with id : "+claimId));
		 return ClaimMapper.toClaimDto(claimFound);
	} 

	public ClaimDto updateClaimByClaimId(Long claimId, ClaimDto claimDto) {
	    Claim existClaim = claimRepository.findById(claimId)
	            .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

	    if (claimDto.getClaimAmount() != null) {
	        existClaim.setClaimAmount(claimDto.getClaimAmount());
	    }
	    if (claimDto.getClaimNumber() != 0) {
	        existClaim.setClaimNumber(claimDto.getClaimNumber());
	    }
	    if (claimDto.getClaimDescription() != null) {
	        existClaim.setClaimDescription(claimDto.getClaimDescription());
	    }
	    if (claimDto.getClaimStatus() != null) {
	        existClaim.setClaimStatus(claimDto.getClaimStatus());
	    }
	    if (claimDto.getClaimDate() != null) {
	        existClaim.setClaimDate(claimDto.getClaimDate());
	    }
	    
	    if (claimDto.getInsurancePolicy() != null) {
	       
	        existClaim.setInsurancePolicy(claimDto.getInsurancePolicy());
	    }

	    Claim updatedClaim = claimRepository.save(existClaim);

	    return ClaimMapper.toClaimDto(updatedClaim);
	}


	public void deleteClaimByClaimId(Long claimId) {
	   
		 Claim deleteClaim = claimRepository.findById(claimId)
                 .orElseThrow(()-> new ClaimNotFoundException("Claim not found with id : "+claimId));

		  claimRepository.delete(deleteClaim);
		
	}
        

}
