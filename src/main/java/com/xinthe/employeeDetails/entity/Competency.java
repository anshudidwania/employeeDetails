package com.xinthe.employeeDetails.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Competency {
    @Id
    @GeneratedValue
    private Integer competencieId;
    private String competencieName;
}
