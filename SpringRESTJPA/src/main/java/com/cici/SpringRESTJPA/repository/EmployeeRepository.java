package com.cici.SpringRESTJPA.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cici.SpringRESTJPA.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
 
}