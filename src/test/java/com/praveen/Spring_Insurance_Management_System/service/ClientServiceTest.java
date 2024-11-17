
package com.praveen.Spring_Insurance_Management_System.service;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.praveen.Spring_Insurance_Management_System.dto.ClientDto;
import com.praveen.Spring_Insurance_Management_System.entity.Client;
import com.praveen.Spring_Insurance_Management_System.entity.InsurancePolicy;
import com.praveen.Spring_Insurance_Management_System.exception.ClientNotFoundException;
import com.praveen.Spring_Insurance_Management_System.exception.InsurancePolicyNotFoundException;
import com.praveen.Spring_Insurance_Management_System.mapper.ClientMapper;
import com.praveen.Spring_Insurance_Management_System.repository.ClientRepository;
import com.praveen.Spring_Insurance_Management_System.repository.InsurancePolicyRepository;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private ClientDto clientDto;
    private InsurancePolicy insurancePolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Client
        client = new Client();
        client.setClientId(1L);
        client.setClientName("John Doe");
        client.setClientAddress("123 Elm Street");
        client.setClientMobileNumber("9876543210");

        // Mock Insurance Policy
        insurancePolicy = new InsurancePolicy();
        insurancePolicy.setInsurancePolicyId(1L);
        insurancePolicy.setInsurancePolicyNumber("POL12345");
        client.setInsurancePolicy(insurancePolicy);

        // Mock ClientDto
        clientDto = ClientMapper.toClientDto(client);
    }

    @Test
    void testCreateClient() {
        when(insurancePolicyRepository.findById(insurancePolicy.getInsurancePolicyId()))
                .thenReturn(Optional.of(insurancePolicy));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto createdClient = clientService.createClient(clientDto);

        assertNotNull(createdClient);
        assertEquals(clientDto.getClientName(), createdClient.getClientName());
        assertEquals(clientDto.getInsurancePolicy().getInsurancePolicyNumber(),
                createdClient.getInsurancePolicy().getInsurancePolicyNumber());
    }

    @Test
    void testCreateClient_PolicyNotFound() {
        when(insurancePolicyRepository.findById(insurancePolicy.getInsurancePolicyId()))
                .thenReturn(Optional.empty());

        assertThrows(InsurancePolicyNotFoundException.class, () -> clientService.createClient(clientDto));
    }

    @Test
    void testGetAllClientsByPolicyId() {
        when(insurancePolicyRepository.existsById(insurancePolicy.getInsurancePolicyId())).thenReturn(true);
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        List<ClientDto> clients = clientService.getAllClientsByPolicyId(insurancePolicy.getInsurancePolicyId());

        assertNotNull(clients);
        assertEquals(1, clients.size());
        assertEquals(client.getClientName(), clients.get(0).getClientName());
    }

    @Test
    void testGetAllClientsByPolicyId_PolicyNotFound() {
        when(insurancePolicyRepository.existsById(insurancePolicy.getInsurancePolicyId())).thenReturn(false);

        assertThrows(InsurancePolicyNotFoundException.class,
                () -> clientService.getAllClientsByPolicyId(insurancePolicy.getInsurancePolicyId()));
    }

    @Test
    void testGetClientByClientId() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        ClientDto foundClient = clientService.getClientByClientId(client.getClientId());

        assertNotNull(foundClient);
        assertEquals(client.getClientName(), foundClient.getClientName());
    }

    @Test
    void testGetClientByClientId_ClientNotFound() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByClientId(client.getClientId()));
    }

    @Test
    void testUpdateClientByClientId() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto updatedClient = clientService.updateClientByClientId(client.getClientId(), clientDto);

        assertNotNull(updatedClient);
        assertEquals(clientDto.getClientName(), updatedClient.getClientName());
    }

    @Test
    void testUpdateClientByClientId_ClientNotFound() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class,
                () -> clientService.updateClientByClientId(client.getClientId(), clientDto));
    }

    @Test
    void testDeleteClientByClientId() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        assertDoesNotThrow(() -> clientService.deleteClientByClientId(client.getClientId()));

        verify(clientRepository, times(1)).delete(client);
    }

    @Test
    void testDeleteClientByClientId_ClientNotFound() {
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.deleteClientByClientId(client.getClientId()));
    }

    @Test
    void testGetClientByClientMobileNumber() {
        when(clientRepository.findClientByClientMobileNumber(client.getClientMobileNumber()))
                .thenReturn(Optional.of(client));

        ClientDto foundClient = clientService.getClientByClientMobileNumber(client.getClientMobileNumber());

        assertNotNull(foundClient);
        assertEquals(client.getClientName(), foundClient.getClientName());
    }

    @Test
    void testGetClientByClientMobileNumber_ClientNotFound() {
        when(clientRepository.findClientByClientMobileNumber(client.getClientMobileNumber()))
                .thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class,
                () -> clientService.getClientByClientMobileNumber(client.getClientMobileNumber()));
    }
    
    @AfterEach
    void tearDown() {
        // Reset any mock interactions
        reset(clientRepository, insurancePolicyRepository);
    }

}

