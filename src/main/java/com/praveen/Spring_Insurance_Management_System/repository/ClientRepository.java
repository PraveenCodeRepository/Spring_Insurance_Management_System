package com.praveen.Spring_Insurance_Management_System.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.praveen.Spring_Insurance_Management_System.entity.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query(value="SELECT * FROM client_tb WHERE client_contact_information = :clientMobileNumber",nativeQuery = true)
	Optional<Client> findClientByClientMobileNumber(@Param("clientMobileNumber") String clientMobileNumber);

}
