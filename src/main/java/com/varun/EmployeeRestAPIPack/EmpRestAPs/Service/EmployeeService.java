package com.varun.EmployeeRestAPIPack.EmpRestAPs.Service;

import com.varun.EmployeeRestAPIPack.EmpRestAPs.Entity.Employee;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeDtos;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public EmployeeDtos createEmployees(EmployeeDtos employeeDtos);
    public EmployeeResponse getListofEmployees(EmployeeDtos employeeDtos, int pageNo, int pagSize,String sorBy,String sortDir);

    public EmployeeDtos getEmployeeById(EmployeeDtos employeeDtos,long empId);
    public EmployeeDtos updateEmployeeById(EmployeeDtos employeeDtos,long empId);
    public String deleteEmployeeById(EmployeeDtos employeeDtos,long empId);



}
