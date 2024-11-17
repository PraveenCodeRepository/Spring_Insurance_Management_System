package com.praveen.Spring_Insurance_Management_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.praveen.Spring_Insurance_Management_System.dto.ClientDto;
import com.praveen.Spring_Insurance_Management_System.entity.Client;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.ClientNotFoundException;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.mapper.ClientMapper;
import com.praveen.Spring_Insurance_Management_System.repository.ClientRepository;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	public ClientDto createClient(ClientDto clientDto) {
	    
	    if (clientDto.getInsurancePolicy() != null && clientDto.getInsurancePolicy().getInsurancePolicyId() != null) {
	        InsurancePolicy existingPolicy = insurancePolicyRepository.findById(clientDto.getInsurancePolicy().getInsurancePolicyId())
	                .orElseThrow(() -> new InsurancePolicyNotFoundException("Insurance Policy not found with id: " + clientDto.getInsurancePolicy().getInsurancePolicyId()));
	        
	     
	        clientDto.setInsurancePolicy(existingPolicy);
	    }

	    
	    Client client = ClientMapper.toClientEntity(clientDto);

	    
	    Client clientSaved = clientRepository.save(client);

	  
	    return ClientMapper.toClientDto(clientSaved);
	}


	public List<ClientDto> getAllClientsByPolicyId(Long policyId) {
		  if(insurancePolicyRepository.existsById(policyId)) {
			 List<Client> clients = clientRepository.findAll();
			return ClientMapper.toClientDtoList(clients);
		  }else {
			  throw new InsurancePolicyNotFoundException("Policy is not found with these "+policyId+" policy id");
		  }
	}

	public ClientDto getClientByClientId(Long clientId) {
		 
		 Client clientFound = clientRepository.findById(clientId)
		                  .orElseThrow(()-> new ClientNotFoundException("Client not found with id : "+clientId));
		 return ClientMapper.toClientDto(clientFound);
	} 
	
	public ClientDto updateClientByClientId(Long clientId, ClientDto clientDto) {
	    Client existClient = clientRepository.findById(clientId)
	            .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));

	    if (clientDto.getClientName() != null) {
	        existClient.setClientName(clientDto.getClientName());
	    }
	    if (clientDto.getClientAddress() != null) {
	        existClient.setClientAddress(clientDto.getClientAddress());
	    }
	    if (clientDto.getClientMobileNumber() != null) {
	        existClient.setClientMobileNumber(clientDto.getClientMobileNumber());
	    }
	    if (clientDto.getClientDateOfBirth() != null) {
	        existClient.setClientDateOfBirth(clientDto.getClientDateOfBirth());
	    }

	    if (clientDto.getInsurancePolicy() != null) {
	        InsurancePolicy existingPolicy = existClient.getInsurancePolicy();
	        if (existingPolicy != null) {
	            if (clientDto.getInsurancePolicy().getInsurancePolicyNumber() != null) {
	                existingPolicy.setInsurancePolicyNumber(clientDto.getInsurancePolicy().getInsurancePolicyNumber());
	            }
	            if (clientDto.getInsurancePolicy().getInsurancePolicyType() != null) {
	                existingPolicy.setInsurancePolicyType(clientDto.getInsurancePolicy().getInsurancePolicyType());
	            }
	            if (clientDto.getInsurancePolicy().getInsurancePolicyCoverageAmount() != 0) {
	                existingPolicy.setInsurancePolicyCoverageAmount(clientDto.getInsurancePolicy().getInsurancePolicyCoverageAmount());
	            }
	            if (clientDto.getInsurancePolicy().getInsurancePolicyPremium() != null) {
	                existingPolicy.setInsurancePolicyPremium(clientDto.getInsurancePolicy().getInsurancePolicyPremium());
	            }
	            if (clientDto.getInsurancePolicy().getInsurancePolicyStartDate() != null) {
	                existingPolicy.setInsurancePolicyStartDate(clientDto.getInsurancePolicy().getInsurancePolicyStartDate());
	            }
	            if (clientDto.getInsurancePolicy().getInsurancePolicyEndDate() != null) {
	                existingPolicy.setInsurancePolicyEndDate(clientDto.getInsurancePolicy().getInsurancePolicyEndDate());
	            }
	        } else {
	            // If the existing policy is null, set the new one directly
	            existClient.setInsurancePolicy(clientDto.getInsurancePolicy());
	        }
	    }

	    Client updatedClient = clientRepository.save(existClient);
	    return ClientMapper.toClientDto(updatedClient);
	}

	

		public void deleteClientByClientId(Long clientId) {
	   
		 Client deleteClient = clientRepository.findById(clientId)
                 .orElseThrow(()-> new ClientNotFoundException("Client not found with id : "+clientId));

		  clientRepository.delete(deleteClient);
		
	}


		public ClientDto getClientByClientMobileNumber(String clientMobileNumber) {
			
			Client clientFound = clientRepository.findClientByClientMobileNumber(clientMobileNumber)
	                  .orElseThrow(()-> new ClientNotFoundException("Client not found with mobile number : "+clientMobileNumber));
	            return ClientMapper.toClientDto(clientFound);
		}
        
	
}
