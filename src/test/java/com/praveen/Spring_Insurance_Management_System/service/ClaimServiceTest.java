package com.praveen.Spring_Insurance_Management_System.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.praveen.Spring_Insurance_Management_System.dto.ClaimDto;
import com.praveen.Spring_Insurance_Management_System.entity.Claim;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.ClaimNotFoundException;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.mapper.ClaimMapper;
import com.praveen.Spring_Insurance_Management_System.repository.ClaimRepository;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private ClaimService claimService;

    private Claim claim;
    private ClaimDto claimDto;
    private InsurancePolicy insurancePolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize entities and DTOs
        insurancePolicy = new InsurancePolicy();
        insurancePolicy.setInsurancePolicyId(1L);

        claim = new Claim();
        claim.setClaimId(1L);
        claim.setClaimNumber(101);
        claim.setClaimDescription("Test Description");
        claim.setClaimDate("31/10/1994");
        claim.setClaimStatus("Approved");
        claim.setClaimAmount("5000");
        claim.setInsurancePolicy(insurancePolicy);

        claimDto = ClaimMapper.toClaimDto(claim);
    }

    @Test
    void testCreateClaim() {
        when(insurancePolicyRepository.findById(1L)).thenReturn(Optional.of(insurancePolicy));
        when(claimRepository.save(any(Claim.class))).thenReturn(claim);

        ClaimDto createdClaim = claimService.createClaim(claimDto);

        assertNotNull(createdClaim);
        assertEquals(claim.getClaimId(), createdClaim.getClaimId());
        assertEquals(claim.getClaimNumber(), createdClaim.getClaimNumber());
        verify(claimRepository, times(1)).save(any(Claim.class));
    }

    @Test
    void testCreateClaim_InsurancePolicyNotFound() {
        when(insurancePolicyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InsurancePolicyNotFoundException.class, () -> claimService.createClaim(claimDto));
    }

    @Test
    void testGetAllClaimsByPolicyId() {
        when(insurancePolicyRepository.existsById(1L)).thenReturn(true);
        when(claimRepository.findAll()).thenReturn(Collections.singletonList(claim));

        Set<ClaimDto> claims = claimService.getAllClaimsByPolicyId(1L);

        assertNotNull(claims);
        assertEquals(1, claims.size());
        verify(claimRepository, times(1)).findAll();
    }

    @Test
    void testGetAllClaimsByPolicyId_PolicyNotFound() {
        when(insurancePolicyRepository.existsById(1L)).thenReturn(false);

        assertThrows(InsurancePolicyNotFoundException.class, () -> claimService.getAllClaimsByPolicyId(1L));
    }

    @Test
    void testGetClaimByClaimId() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        ClaimDto foundClaim = claimService.getClaimByClaimId(1L);

        assertNotNull(foundClaim);
        assertEquals(claim.getClaimId(), foundClaim.getClaimId());
        verify(claimRepository, times(1)).findById(1L);
    }

    @Test
    void testGetClaimByClaimId_NotFound() {
        when(claimRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClaimNotFoundException.class, () -> claimService.getClaimByClaimId(1L));
    }

    @Test
    void testUpdateClaimByClaimId() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));
        when(claimRepository.save(any(Claim.class))).thenReturn(claim);

        claimDto.setClaimStatus("Rejected");
        ClaimDto updatedClaim = claimService.updateClaimByClaimId(1L, claimDto);

        assertNotNull(updatedClaim);
        assertEquals("Rejected", updatedClaim.getClaimStatus());
        verify(claimRepository, times(1)).save(any(Claim.class));
    }

    @Test
    void testUpdateClaimByClaimId_NotFound() {
        when(claimRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClaimNotFoundException.class, () -> claimService.updateClaimByClaimId(1L, claimDto));
    }

    @Test
    void testDeleteClaimByClaimId() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        claimService.deleteClaimByClaimId(1L);

        verify(claimRepository, times(1)).delete(claim);
    }

    @Test
    void testDeleteClaimByClaimId_NotFound() {
        when(claimRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClaimNotFoundException.class, () -> claimService.deleteClaimByClaimId(1L));
    }
    
    @AfterEach
    public void tearDown() {
    	reset(claimRepository,insurancePolicyRepository);
    }
}
