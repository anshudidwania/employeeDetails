package com.xinthe.employeeDetails.dao;

import com.xinthe.employeeDetails.entity.Competency;
import com.xinthe.employeeDetails.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetencyRepository extends JpaRepository<Competency, Integer> {
}
