package com.cici.SpringRESTJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cici.SpringRESTJPA.exception.ResourceNotFoundException;
import com.cici.SpringRESTJPA.model.Employee;
import com.cici.SpringRESTJPA.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/cici/api")
public class EmployeeController{
	
	

    @Autowired
    private EmployeeRepository employeeRepository;
 
    //GET ALL EMPLOYEE
 
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(Model model) {
        return this.employeeRepository.findAll();
    }
 
    
    // GET EMPLOYEE BY ID
       
       
       @GetMapping("/employees/{id}")
       public ResponseEntity<Employee> getEmployeeById1(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
         Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
         return ResponseEntity.ok().body(employee);
       }
       
       
      // save employee
       
       
       @PostMapping("/employees")
       public Employee createEmployee(@Validated @RequestBody Employee employee) {
      	 return employeeRepository.save(employee);
       }
       
       //Save Employee by ID --we might not need this one
       
       @PostMapping("/employees/{id}")
       Employee createOrSaveEmployee(@RequestBody Employee newEmployee) {
           return this.employeeRepository.save(newEmployee);   
           
       }
    
    
    // UPDATE employee by ID

       @PutMapping("/employees/{id}")
       Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
           return employeeRepository.findById(id).map(employee -> {
               employee.setFirstName(newEmployee.getFirstName());
               employee.setLastName(newEmployee.getLastName());
               employee.setDepartment(newEmployee.getDepartment());
               return employeeRepository.save(employee);
           }).orElseGet(() -> {
               newEmployee.setId(id);
               return employeeRepository.save(newEmployee);
           });
       }
    
    
       
       
       //Delete All Employees 
       @DeleteMapping("/employees")
       void deleteEmployee() {
           employeeRepository.deleteAll();
       }
       
   
       //Delete Employee By ID
    
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
    
}






    
    
    
    
    
