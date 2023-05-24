package com.varun.EmployeeRestAPIPack.EmpRestAPs.Repository;

import com.varun.EmployeeRestAPIPack.EmpRestAPs.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
