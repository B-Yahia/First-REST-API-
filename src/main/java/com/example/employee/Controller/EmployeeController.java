package com.example.employee.Controller;

import com.example.employee.Model.Employee;
import com.example.employee.Service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping(value = "appDetails")
    public String getAppDetails(){
        return appName+" "+appVersion;
    }


    @PostMapping(value="/emp")
    public ResponseEntity< String> createEmployee(@Valid @RequestBody Employee emp) {
        employeeService.createEmployee(emp);
       return new ResponseEntity<String>("Added",HttpStatus.CREATED);
    }

    @GetMapping(value="/emp")
    public ResponseEntity<List<Employee>> readEmployees(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNum,pageSize), HttpStatus.OK);
    }

    @PutMapping(value="/emp/{empId}")
    public ResponseEntity<Employee> readEmployees(@PathVariable(value = "empId") int id, @RequestBody Employee empDetails) {
        return new ResponseEntity<Employee>( employeeService.updateEmployee(id, empDetails),HttpStatus.OK);
    }

    @DeleteMapping(value="/emp/{empId}")
    public ResponseEntity<String> deleteEmployees(@PathVariable(value = "empId") int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee with the ID : "+ id +" has been deleted",HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/deleteAll")
    public ResponseEntity<String> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<String>("All Employees were removed",HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/empByName/{name}")
    public ResponseEntity<List<Employee>> finEmployeByName(@PathVariable (value = "name") String name){
        return new ResponseEntity<List<Employee>>(employeeService.findEmployeeByName(name),HttpStatus.OK);
    }
    @GetMapping(value = "/empByName")
    public ResponseEntity<List<Employee>> finEmployeByNameContainKeyword(@RequestParam  String keyword){
        return new ResponseEntity<List<Employee>>(employeeService.findEmployeeByNameContainsKeyword(keyword),HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteByName/{name}")
    public ResponseEntity<String>deleteEmployeeByName(@PathVariable String name){
        String returnString = "the number of the employee deleted from the DB is : "+ employeeService.deleteEmployeeByName(name);
        return new ResponseEntity<String>(returnString,HttpStatus.OK);
    }
}
