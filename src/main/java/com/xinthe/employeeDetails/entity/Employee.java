package com.xinthe.employeeDetails.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Integer employeeId;
    private String employeeName;
    private String title;
    private String businessUnit;
    private String place;
    private Integer supervisorId;
    @OneToMany(mappedBy = "employeeId")
    private List<EmployeeCompetencyMapping> competencies;
    private Double salary;
}
