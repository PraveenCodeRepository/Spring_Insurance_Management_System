package com.praveen.Spring_Insurance_Management_System.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.praveen.Spring_Insurance_Management_System.dto.ClientDto;
import com.praveen.Spring_Insurance_Management_System.entity.Client;

@Component
public class ClientMapper {
	
	public static ClientDto toClientDto(Client client) {
		
		 if(client==null) return null;
		 
		ClientDto clientDto = new ClientDto();
		clientDto.setClientId(client.getClientId());
		clientDto.setClientDateOfBirth(client.getClientDateOfBirth());
		clientDto.setClientName(client.getClientName());
		clientDto.setClientAddress(client.getClientAddress());
		clientDto.setClientMobileNumber(client.getClientMobileNumber());
		
		if(client.getInsurancePolicy()!=null)
		   clientDto.setInsurancePolicy(client.getInsurancePolicy());
		
		return clientDto;
		
	}
	
	public static Client toClientEntity(ClientDto clientDto) {
		
		 if(clientDto==null) return null;
		 
			Client client = new Client();
			client.setClientId(clientDto.getClientId());
			client.setClientName(clientDto.getClientName());
			client.setClientDateOfBirth(clientDto.getClientDateOfBirth());
			client.setClientAddress(clientDto.getClientAddress());
			client.setClientMobileNumber(clientDto.getClientMobileNumber());
			
			if(clientDto!=null)
			client.setInsurancePolicy(clientDto.getInsurancePolicy());
			
			return client;
	}
	
	public static List<ClientDto> toClientDtoList(List<Client> clients){
		  
		    if(clients==null) return null;
		    
		    List<ClientDto> clientDtos = new ArrayList<>();
		    
		       for(Client client:clients) {
		    	   clientDtos.add(ClientMapper.toClientDto(client));
		       }
		       
		       return clientDtos;
	}
     
	public static List<Client> toClientEntityList(List<ClientDto> clientDtos){
		  
	    if(clientDtos==null) return null;
	    
	    List<Client> clients = new ArrayList<>();
	    
	       for(ClientDto clientDto:clientDtos) {
	    	   clients.add(ClientMapper.toClientEntity(clientDto));
	       }
	       
	       return clients;
}

}
