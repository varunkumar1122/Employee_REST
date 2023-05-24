package com.varun.EmployeeRestAPIPack.EmpRestAPs.Controller;

import com.varun.EmployeeRestAPIPack.EmpRestAPs.Constants.AppConstants;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeDtos;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeResponse;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDtos>createEmployees(@Valid @RequestBody EmployeeDtos employeeDtos){
        return new ResponseEntity<>(employeeService.createEmployees(employeeDtos), HttpStatus.CREATED);

    }
    @GetMapping("/getListOfEmployees")
    public ResponseEntity<EmployeeResponse> getListofEmployees( @RequestBody EmployeeDtos employeeDtos,
                                                               @RequestParam(value="pageNo", defaultValue
                                                                       = AppConstants.default_PageNo,
                                                                         required = false) int pageSize,
                                                               @RequestParam(value="pageSize",defaultValue
                                                                       =AppConstants.default_pageSize,
                                                                         required = false) int pageNo,
                                                               @RequestParam(value="sortBy",defaultValue
                                                                       =AppConstants.default_SortBy,
                                                                       required = false) String sortBy,
                                                               @RequestParam(value="sortDir",defaultValue
                                                                       =AppConstants.default_SortDir,
                                                                       required = false) String sortDir){
        return new ResponseEntity<>(employeeService.getListofEmployees(employeeDtos,pageSize,pageNo,sortBy,sortDir),HttpStatus.OK);
    }
    @GetMapping("/getEmpById/{id}")
    public ResponseEntity<EmployeeDtos> getEmployeeById(@RequestBody EmployeeDtos employeeDtos,
                                                        @PathVariable("id")long empId){
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeDtos,empId),HttpStatus.OK);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDtos> updateEmployeeById(@Valid @RequestBody EmployeeDtos employeeDtos,
                                                           @PathVariable("id") long empId){
        return new ResponseEntity<>(employeeService.updateEmployeeById(employeeDtos,empId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@RequestBody EmployeeDtos employeeDtos,
                                                     @PathVariable("id") long empId){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(employeeDtos,empId),HttpStatus.OK);
    }
}
