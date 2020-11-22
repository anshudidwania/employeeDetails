package com.xinthe.employeeDetails.vos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeVO {
    private Integer employeeId;
    private String employeeName;
    private String title;
    private String businessUnit;
    private String place;
    private Integer supervisorId;
    private Double salary;
}
