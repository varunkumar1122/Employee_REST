package com.varun.EmployeeRestAPIPack.EmpRestAPs.Payload;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private List<EmployeeDtos> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean last;
}
