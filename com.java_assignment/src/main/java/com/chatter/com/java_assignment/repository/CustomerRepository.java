package com.chatter.com.java_assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatter.com.java_assignment.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
