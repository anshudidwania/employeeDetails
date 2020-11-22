package com.xinthe.employeeDetails.dao;

import com.xinthe.employeeDetails.entity.Employee;
import com.xinthe.employeeDetails.vos.SalaryRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "from Employee where place = :place")
    List<Employee> getEmployeeByPlace(@Param("place") String place);

    @Query(value = "select new com.xinthe.employeeDetails.vos.SalaryRange (max(e.salary) as maxSalary, min(e.salary) as minSalary)" +
            " from Employee e " +
            "inner join EmployeeCompetencyMapping ecm on e.employeeId = ecm.employeeId "+
            "where ecm.competencieId = :competency")
    SalaryRange getSalaryRangeByCompetency(@Param("competency") Integer competency);
}
