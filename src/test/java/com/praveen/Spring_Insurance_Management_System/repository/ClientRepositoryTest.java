package com.praveen.Spring_Insurance_Management_System.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.praveen.Spring_Insurance_Management_System.entity.Client;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;

import jakarta.transaction.Transactional;



@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ClientRepositoryTest {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;
	
	private Client client;
	
	@BeforeEach
	public void setUp() {
		
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		insurancePolicy.setInsurancePolicyId((long) 2550);
		insurancePolicy.setInsurancePolicyType("Health");
		insurancePolicy.setInsurancePolicyNumber("P_001");
		insurancePolicy.setInsurancePolicyPremium("10000");
		insurancePolicy.setInsurancePolicyCoverageAmount(1500000);
		insurancePolicy.setInsurancePolicyStartDate("31/10/1994");
		insurancePolicy.setInsurancePolicyEndDate("30/10/64");
		
		InsurancePolicy associatePolicy =insurancePolicyRepository.save(insurancePolicy);
		
		
		client = new Client();
		client.setClientName("Praveen");
		client.setClientAddress("Chennai");
		client.setClientDateOfBirth("31/10/1994");
		client.setClientMobileNumber("1234567890");
		client.setInsurancePolicy(associatePolicy);
		
		
		clientRepository.save(client);
	
	}
	
	@Test
	public void testFindClientByClientMobileNumber() {
		
		Optional<Client> clientFound = clientRepository.findClientByClientMobileNumber("1234567890");
		
		assertThat(clientFound).isPresent();
		assertThat(clientFound.get().getClientName()).isEqualTo("Praveen");
		assertThat(clientFound.get().getClientMobileNumber()).isEqualTo("1234567890");
		assertThat(clientFound.get().getInsurancePolicy().getInsurancePolicyType()).isEqualTo("Health");

		
	}
	
	@Test
	public void testReturnEmptyIfClientNotFound() {
		
		Optional<Client> clientFound = clientRepository.findClientByClientMobileNumber("1234567899");
		
		assertThat(clientFound).isEmpty();

		
	}
	
	@AfterEach
	public void tearDown() {
		clientRepository.deleteAll();
		insurancePolicyRepository.deleteAll();
	}

}
