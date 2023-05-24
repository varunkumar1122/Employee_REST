package com.varun.EmployeeRestAPIPack.EmpRestAPs.ServiceImpl;

import com.varun.EmployeeRestAPIPack.EmpRestAPs.Entity.Employee;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Exception.ResourceNotFoundException;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeDtos;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload.EmployeeResponse;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Repository.EmployeeRepository;
import com.varun.EmployeeRestAPIPack.EmpRestAPs.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.AbstractDocument;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public EmployeeDtos createEmployees(EmployeeDtos employeeDtos) {
        Employee saveEmployee=mapToEntity(employeeDtos);
        Employee savedEmployee=employeeRepository.save(saveEmployee);
        EmployeeDtos newEmployeeDtos=mapToDtos(savedEmployee);
        return newEmployeeDtos;
    }

    @Override
    public EmployeeResponse getListofEmployees(EmployeeDtos employeeDtos, int pageNo, int pageSize,
                                               String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
        Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();
        // implementing pagination
        // first make use of pagebale object
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        // pagable returns  page of employees
        Page<Employee> employeePage=employeeRepository.findAll(pageable);
        // getting list of employees from page of employees
        List<Employee> listEmp=employeePage.getContent();

       // converting list of employees to list of employee dtos
           List<EmployeeDtos> content=listEmp.stream()
                .map(Employee->mapToDtos(Employee)).collect(Collectors.toList());
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setContent(content);
        employeeResponse.setPageNo(employeePage.getNumber());
        employeeResponse.setPageSize(employeePage.getSize());
        employeeResponse.setTotalElements((int) employeePage.getTotalElements());
        employeeResponse.setTotalPages(employeePage.getTotalPages());
        employeeResponse.setLast(employeePage.isLast());
        return employeeResponse;
    }

    @Override
    public EmployeeDtos getEmployeeById(EmployeeDtos employeeDtos, long empId) {
        Employee employeeById=employeeRepository.findById(empId).
                orElseThrow(()->new ResourceNotFoundException("Employee","empId",empId));

        return mapToDtos(employeeById);
    }

    @Override
    public EmployeeDtos updateEmployeeById(EmployeeDtos employeeDtos, long empId) {
        Employee upadteEmployee=employeeRepository.findById(empId).
                orElseThrow(()-> new ResourceNotFoundException("Employee","empId",empId));
        upadteEmployee.setEmpName(employeeDtos.getEmpName());
        upadteEmployee.setEmpEmailId(employeeDtos.getEmpEmailId());
        Employee upadtedEmployee=employeeRepository.save(upadteEmployee);
        return mapToDtos(upadtedEmployee);
    }

    @Override
    public String deleteEmployeeById(EmployeeDtos employeeDtos, long empId) {
        Employee employee =employeeRepository.findById(empId).
                orElseThrow(()-> new ResourceNotFoundException("Employee","empId",empId));
        employeeRepository.delete(employee);
        return "Employee Record deleted successfully";
    }

    // converting entities to Dtos
    private EmployeeDtos mapToDtos(Employee employee){
        EmployeeDtos employeeDtos=modelMapper.map(employee,EmployeeDtos.class);
        return employeeDtos;
    }
    // converting Dtos to entities
    private Employee mapToEntity(EmployeeDtos employeeDtos){
        Employee employee=modelMapper.map(employeeDtos,Employee.class);
        return employee;
    }
}
