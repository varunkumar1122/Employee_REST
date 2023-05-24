package com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDtos {
    private long empId;
    @NotEmpty
    @Size(min = 4,message = "name should  atleast contains 4 characters")
    private String empName;
    @Email
    @NotEmpty
    @Size(min = 12,message="email should contain 10 characters")
    private String empEmailId;

}
