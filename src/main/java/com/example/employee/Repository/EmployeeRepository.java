package com.example.employee.Repository;

import com.example.employee.Model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

    //JPA QUery methode to find users by name
    List<Employee> findByName(String name);

    //Jpa Query methode to find by name LIKE %name%
    List<Employee> findByNameContaining(String keyword, Sort sort);

    //Write JPQL select query
    @Query("From Employee WHERE name=:name OR email=:email")
    List<Employee> getEmployeesByNameAndEmail(String name,String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteEmployeeByName(String name);

}
