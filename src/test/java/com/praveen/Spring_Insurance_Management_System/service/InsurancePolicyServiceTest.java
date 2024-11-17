package com.praveen.Spring_Insurance_Management_System.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.praveen.Spring_Insurance_Management_System.dto.InsurancePolicyDto;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
public class InsurancePolicyServiceTest {
	
	@Mock
	private InsurancePolicyRepository  insurancePolicyRepository;
	
	@InjectMocks
	private InsurancePolicyService insurancePolicyService;
	
	private InsurancePolicy insurancePolicy;
	
	private InsurancePolicyDto insurancePolicyDto;
	
	@BeforeEach
	public void setup() {
		
		insurancePolicy = new InsurancePolicy();
		insurancePolicy.setInsurancePolicyId((long) 2550);
		insurancePolicy.setInsurancePolicyType("Health");
		insurancePolicy.setInsurancePolicyNumber("P_001");
		insurancePolicy.setInsurancePolicyPremium("10000");
		insurancePolicy.setInsurancePolicyCoverageAmount(1500000);
		insurancePolicy.setInsurancePolicyStartDate("31/10/1994");
		insurancePolicy.setInsurancePolicyEndDate("30/10/64");
		
        insurancePolicyDto = new InsurancePolicyDto();
		insurancePolicyDto.setInsurancePolicyId((long) 2550);
		insurancePolicyDto.setInsurancePolicyType("Health");
		insurancePolicyDto.setInsurancePolicyNumber("P_001");
		insurancePolicyDto.setInsurancePolicyPremium("10000");
		insurancePolicyDto.setInsurancePolicyCoverageAmount(1500000);
		insurancePolicyDto.setInsurancePolicyStartDate("31/10/1994");
		insurancePolicyDto.setInsurancePolicyEndDate("30/10/64");

	}
    
	@Test
	public void testCreateInsurancePolicy() {
		
		when(insurancePolicyRepository.save( any(InsurancePolicy.class) ) ).thenReturn(insurancePolicy); //Arrange
		 
		InsurancePolicyDto insurancePolicyCreated =insurancePolicyService.createInsurancePolicy(insurancePolicyDto); //Act
		
		assertNotNull(insurancePolicyCreated); //Assert
		assertEquals(insurancePolicyDto.getInsurancePolicyType(),insurancePolicyCreated.getInsurancePolicyType());
		assertEquals(insurancePolicyDto.getInsurancePolicyCoverageAmount(), insurancePolicyCreated.getInsurancePolicyCoverageAmount());
		
	}
	
	@Test
	public void testGetAllPolicies() {
		 
		   List<InsurancePolicy> insurancePolicyList =  Collections.singletonList(insurancePolicy);
		   
		   when(insurancePolicyRepository.findAll()).thenReturn(insurancePolicyList); //Arrange
		   
            Set<InsurancePolicyDto> insurancePolicySet =  insurancePolicyService.getAllPolicies();//Act
            
            assertNotNull(insurancePolicySet);          //Assert
            assertFalse(insurancePolicySet.isEmpty());
            assertEquals(1,insurancePolicySet.size());
	}
	
	@Test
	public void testGetPolicyByPolicyId_Success() {
		
		when(insurancePolicyRepository.findById((long) 2550)).thenReturn(Optional.of(insurancePolicy));
		
	       InsurancePolicyDto insurancePolicyDto = insurancePolicyService.getPolicyByPolicyId((long) 2550);
	       
	       assertNotNull(insurancePolicyDto);
	       assertEquals(insurancePolicy.getInsurancePolicyNumber(),insurancePolicyDto.getInsurancePolicyNumber());
	}
	
	@Test
	public void testGetPolicyByPolicyId_NotFound() {
		 // Arrange
	    Long nonExistentId = 999L;
	    when(insurancePolicyRepository.findById(nonExistentId)).thenReturn(Optional.empty());

	    // Act & Assert
	    InsurancePolicyNotFoundException exception = assertThrows(InsurancePolicyNotFoundException.class, () -> {
	        insurancePolicyService.getPolicyByPolicyId(nonExistentId);
	    });

	    // Verify the exception message
	    assertEquals("Insurance Policy not found with id :" + nonExistentId, exception.getMessage());
	}
	
	@Test
	public void testUpdateInsurancePolicyByPolicyId() {
		
		when(insurancePolicyRepository.findById((long) 2550)).thenReturn(Optional.of(insurancePolicy));
		when(insurancePolicyRepository.save(any(InsurancePolicy.class))).thenReturn(insurancePolicy);
		
		insurancePolicyDto.setInsurancePolicyType("Term");
		insurancePolicyDto.setInsurancePolicyPremium("18000");
		
		InsurancePolicyDto updatedPolicy =insurancePolicyService.updatePolicyByPolicyId((long) 2550, insurancePolicyDto);
		
		assertNotNull(updatedPolicy);
		assertEquals(insurancePolicyDto.getInsurancePolicyType(),updatedPolicy.getInsurancePolicyType());
		assertEquals(insurancePolicyDto.getInsurancePolicyPremium(),updatedPolicy.getInsurancePolicyPremium());
		
		
	}
	
	@Test
	public void testDeleteInsurancePolicyByPolicyId_Success() {
		
		when(insurancePolicyRepository.findById((long) 2550)).thenReturn(Optional.of(insurancePolicy));
		
		insurancePolicyService.deletePolicyByPolicyId((long) 2550);
		
		verify(insurancePolicyRepository,times(1)).delete(insurancePolicy);
	}
	
	@Test
	void testDeletePolicyByPolicyId_NotFound() {
	    // Arrange
	    Long nonExistentId = 999L;
	    when(insurancePolicyRepository.findById(nonExistentId)).thenReturn(Optional.empty());

	    // Act & Assert
	    InsurancePolicyNotFoundException exception = assertThrows(InsurancePolicyNotFoundException.class, () -> {
	        insurancePolicyService.deletePolicyByPolicyId(nonExistentId);
	    });

	    // Verify the exception message
	    assertEquals("Insurance Policy not found with id :" + nonExistentId, exception.getMessage());
	}

	
	@AfterEach
	public void tearDown() {
		Mockito.reset(insurancePolicyRepository);
	}
}
