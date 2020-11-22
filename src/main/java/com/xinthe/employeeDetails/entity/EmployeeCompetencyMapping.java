package com.xinthe.employeeDetails.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class EmployeeCompetencyMapping implements Serializable {
    @Id
    private Integer employeeId;
    @Id
    private Integer competencieId;
}
