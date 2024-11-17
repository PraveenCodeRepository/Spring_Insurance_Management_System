package com.praveen.Spring_Insurance_Management_System.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.Spring_Insurance_Management_System.dto.ClientDto;
import com.praveen.Spring_Insurance_Management_System.entity.Client;
import com.praveen.Spring_Insurance_Management_System.exception.ClientNotFoundException;
import com.praveen.Spring_Insurance_Management_System.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/createClient")
	public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
		 try {
		 ClientDto clientDtoCreated = clientService.createClient(clientDto);
		 return ResponseEntity.status(HttpStatus.CREATED).body(clientDtoCreated);
		 }catch(IllegalArgumentException ex) {
			 return ResponseEntity.badRequest().body(null);
		 }catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
           }
		  
	}
	
  	@GetMapping("getAllClients/{policyId}")
	public ResponseEntity<List<ClientDto>> getAllClientsByPolicyId(@PathVariable Long policyId){
		try {
			List<ClientDto> clientDtosFound =clientService.getAllClientsByPolicyId(policyId);
			return ResponseEntity.status(HttpStatus.OK).body(clientDtosFound);
		}catch(ClientNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@GetMapping("getClient/{clientId}")
	public ResponseEntity<ClientDto> getClientByClientId(@PathVariable Long clientId){
		try {
			ClientDto clientDtoFound =clientService.getClientByClientId(clientId);
			return ResponseEntity.status(HttpStatus.OK).body(clientDtoFound);
		}catch(ClientNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@PutMapping("/updateClient/{clientId}")
	public ResponseEntity<ClientDto> updateClientByClientId(@PathVariable Long clientId,@RequestBody
			ClientDto clientDto){
		 try {
			 ClientDto clientDtoUpdated = clientService.updateClientByClientId(clientId,clientDto);
			 return ResponseEntity.status(HttpStatus.CREATED).body(clientDtoUpdated);
			 }catch(IllegalArgumentException ex) {
				 return ResponseEntity.badRequest().body(null);
			 }catch(Exception ex) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	           }
	}
	
	@DeleteMapping("/deleteClient/{clientId}")
	public ResponseEntity<?> deleteClientByClientId(@PathVariable Long clientId){
		try {
			clientService.deleteClientByClientId(clientId);
			return ResponseEntity.noContent().build();
		}catch(ClientNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@GetMapping("/getClient")
	public ResponseEntity<ClientDto> getClientByClientMobileNumber(@RequestParam String clientMobileNumber){
		try {
			 ClientDto clientDto = clientService.getClientByClientMobileNumber(clientMobileNumber);
			return ResponseEntity.status(HttpStatus.OK).body(clientDto);
		}catch(ClientNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception ex) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

	}


}
