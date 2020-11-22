package com.xinthe.employeeDetails.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRange {
    private Double maxSalary;
    private Double minSalary;
}
