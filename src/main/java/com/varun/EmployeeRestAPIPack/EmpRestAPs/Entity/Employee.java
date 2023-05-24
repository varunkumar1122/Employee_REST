package com.varun.EmployeeRestAPIPack.EmpRestAPs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee",uniqueConstraints ={@UniqueConstraint(columnNames ={"empEmailId"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE,generator ="emp_gen")
    @SequenceGenerator(name = "emp_gen",sequenceName = "emp_seq_gen")
    private long empId;
    @Column(nullable = false)
    private String empName;
    @Column(nullable = false)
    private String empEmailId;
}
