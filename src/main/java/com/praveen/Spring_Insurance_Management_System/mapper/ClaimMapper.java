package com.praveen.Spring_Insurance_Management_System.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.praveen.Spring_Insurance_Management_System.dto.ClaimDto;
import com.praveen.Spring_Insurance_Management_System.entity.Claim;

@Component
public class ClaimMapper {
   
	 public static ClaimDto toClaimDto(Claim claim) {
		  
		  if(claim==null) return null;
		  
		  ClaimDto claimDto = new ClaimDto();
		  claimDto.setClaimId(claim.getClaimId());
		  claimDto.setClaimNumber(claim.getClaimNumber());
		  claimDto.setClaimAmount(claim.getClaimAmount());
		  claimDto.setClaimStatus(claim.getClaimStatus());
		  claimDto.setClaimDescription(claim.getClaimDescription());
		  claimDto.setClaimDate(claim.getClaimDate());
		    
		    if(claim.getInsurancePolicy()!=null)
		  claimDto.setInsurancePolicy(claim.getInsurancePolicy());
		    
		    return claimDto;
	 }
	 
	 public static Claim toClaimEntity(ClaimDto claimDto) {
		  
		  if(claimDto==null) return null;
		  
		  Claim claim = new Claim();
		  claim.setClaimId(claimDto.getClaimId());
		  claim.setClaimNumber(claimDto.getClaimNumber());
		  claim.setClaimAmount(claimDto.getClaimAmount());
		  claim.setClaimStatus(claimDto.getClaimStatus());
		  claim.setClaimDescription(claimDto.getClaimDescription());
		  claim.setClaimDate(claimDto.getClaimDate());
		    
		    if(claimDto.getInsurancePolicy()!=null)
		  claim.setInsurancePolicy(claimDto.getInsurancePolicy());
		    
		    return claim;
	 }
	 
	 public static Set<Claim> toClaimEntitySet(Set<ClaimDto> claimDtos){
		 
		    if(claimDtos==null) return null;
		    
		    Set<Claim> claims = new HashSet<>();
		    
		    for(ClaimDto claimDto:claimDtos) {
		    	claims.add(toClaimEntity(claimDto));
		    }
		    return claims;
	 }
	 
	 public static Set<ClaimDto> toClaimDtoSet(Set<Claim> claims){
		 
		    if(claims==null) return null;
		    
		    Set<ClaimDto> claimDtos = new HashSet<>();
		    
		    for(Claim claim:claims) {
		    	claimDtos.add(toClaimDto(claim));
		    }
		    return claimDtos;
	 }
}
