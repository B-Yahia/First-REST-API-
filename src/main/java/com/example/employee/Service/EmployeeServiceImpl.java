package com.example.employee.Service;

import com.example.employee.Model.Employee;
import com.example.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeRepository employeeRepository;



    // CREATE
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    // READ
    public List<Employee> getEmployees(int pageNumber,int pageSize) {
        Pageable pagesWithoutSorting = PageRequest.of(pageNumber,pageSize);
        Pageable pagesWithSorting = PageRequest.of(pageNumber,pageSize, Sort.Direction.DESC,"name");
        return employeeRepository.findAll(pagesWithSorting).getContent();
    }

    // DELETE
    public void deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }

    // UPDATE
    public Employee updateEmployee(int empId, Employee employeeDetails) {
        Employee emp = employeeRepository.findById(empId).get();
        emp.setName(employeeDetails.getName());
        emp.setEmail(employeeDetails.getEmail());

        return employeeRepository.save(emp);
    }

    // delete all the users
    public void deleteAllEmployees(){
        employeeRepository.deleteAll();
    }

    //Find by name
    public List<Employee> findEmployeeByName(String name){
        return employeeRepository.findByName(name);
    }

    //Find by name that contain a keyword
    public List<Employee> findEmployeeByNameContainsKeyword(String keyword){
        Sort sort= Sort.by(Sort.Direction.DESC,"name");
        return employeeRepository.findByNameContaining(keyword,sort);
    }

    //Find user by name or email
    public  List<Employee> FindUserByNameOrEmail (String name , String email){
        return employeeRepository.getEmployeesByNameAndEmail(name,email);
    }

    //delete employee by name
    public Integer deleteEmployeeByName (String name){
        return employeeRepository.deleteEmployeeByName(name);
    }
}
