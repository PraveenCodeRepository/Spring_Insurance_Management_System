package com.praveen.Spring_Insurance_Management_System.controller;

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

import com.praveen.Spring_Insurance_Management_System.dto.InsurancePolicyDto;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.service.InsurancePolicyService;

@RestController
@RequestMapping("/insurance")
public class InsurancePolicyController {
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	
	@PostMapping("/createPolicy")
	public ResponseEntity<InsurancePolicyDto> createInsurancePolicy(@RequestBody InsurancePolicyDto insurancePolicyDto){
		 try {
		InsurancePolicyDto insurancePolicyDtoCreated = insurancePolicyService.createInsurancePolicy(insurancePolicyDto);
		      return ResponseEntity.status(HttpStatus.CREATED).body(insurancePolicyDtoCreated);
		 }catch(IllegalArgumentException ex) {
			 return ResponseEntity.badRequest().body(null);
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 }
	}
	
	@GetMapping("/getAllPolicies")
	public ResponseEntity<Set<InsurancePolicyDto>> getAllPolicies(){
		try {
		 Set<InsurancePolicyDto> insurancePolicySetFound = insurancePolicyService.getAllPolicies();
		  return ResponseEntity.status(HttpStatus.OK).body(insurancePolicySetFound);
		}catch(InsurancePolicyNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 }

	}
	
	@GetMapping("/getPolicy/{policyId}")
	public ResponseEntity<InsurancePolicyDto> getPolicyByPolicyId(@PathVariable Long policyId){
		try {
		 InsurancePolicyDto insurancePolicyDtoFound = insurancePolicyService.getPolicyByPolicyId(policyId);
		  return ResponseEntity.status(HttpStatus.OK).body(insurancePolicyDtoFound);
		}catch(InsurancePolicyNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 }

	}
	@PutMapping("/updatePolicy/{policyId}")
	public ResponseEntity<InsurancePolicyDto> updatePolicyByPolicyId(@PathVariable Long policyId , @RequestBody
			InsurancePolicyDto insurancePolicyDto){
		try {
			 InsurancePolicyDto insurancePolicyDtoUpdated = insurancePolicyService.updatePolicyByPolicyId(policyId,
					 insurancePolicyDto);
			  return ResponseEntity.status(HttpStatus.OK).body(insurancePolicyDtoUpdated);
			}catch(InsurancePolicyNotFoundException ex) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}catch(Exception ex) {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			 }
	}
	
	@DeleteMapping("/deletePolicy/{policyId}")
	public ResponseEntity<?> deletePolicyByPolicyId(@PathVariable Long policyId){
		try {
			insurancePolicyService.deletePolicyByPolicyId(policyId);
			return ResponseEntity.noContent().build();
		}catch(InsurancePolicyNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 }
	}

}
