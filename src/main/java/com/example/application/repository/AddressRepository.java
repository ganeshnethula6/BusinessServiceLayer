package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
