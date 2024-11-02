package com.praveen.Spring_Insurance_Management_System.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.Spring_Insurance_Management_System.dto.ClaimDto;
import com.praveen.Spring_Insurance_Management_System.exception.ClaimNotFoundException;
import com.praveen.Spring_Insurance_Management_System.exception.ClientNotFoundException;
import com.praveen.Spring_Insurance_Management_System.service.ClaimService;


@RestController
@RequestMapping("/claim")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@PostMapping("/createClaim")
	public ResponseEntity<ClaimDto> createClaim(@RequestBody ClaimDto claimDto){
		 try {
		ClaimDto claimDtoCreated = claimService.createClaim(claimDto);
		 return ResponseEntity.status(HttpStatus.CREATED).body(claimDtoCreated);
		 }catch(IllegalArgumentException ex) {
			 ex.printStackTrace();
			 return ResponseEntity.badRequest().body(null);
		 }catch(Exception ex) {
			 ex.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
           }
		  
	}
	
  	@GetMapping("getAllClaims/{policyId}")
	public ResponseEntity<Set<ClaimDto>> getAllClaimsByPolicyId(@PathVariable Long policyId){
		try {
			Set<ClaimDto> claimDtosFound = claimService.getAllClaimsByPolicyId(policyId);
			return ResponseEntity.status(HttpStatus.OK).body(claimDtosFound);
		}catch(ClaimNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@GetMapping("getClaim/{claimId}")
	public ResponseEntity<ClaimDto> getClaimByClaimId(@PathVariable Long claimId){
		try {
			ClaimDto claimDtoFound =claimService.getClaimByClaimId(claimId);
			return ResponseEntity.status(HttpStatus.OK).body(claimDtoFound);
		}catch(ClaimNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@PutMapping("/updateClaim/{claimId}")
	public ResponseEntity<ClaimDto> updateClaimByClaimId(@PathVariable Long clientId,@RequestBody
			ClaimDto clientDto){
		 try {
			ClaimDto claimDtoUpdated = claimService.updateClaimByClaimId(clientId,clientDto);
			 return ResponseEntity.status(HttpStatus.CREATED).body(claimDtoUpdated);
			 }catch(IllegalArgumentException ex) {
				 return ResponseEntity.badRequest().body(null);
			 }catch(Exception ex) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	           }
	}
	
	@DeleteMapping("/deleteClaim/{claimId}")
	public ResponseEntity<?> deleteClaimByClaimId(@PathVariable Long clientId){
		try {
			claimService.deleteClaimByClaimId(clientId);
			return ResponseEntity.noContent().build();
		}catch(ClaimNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}


}
