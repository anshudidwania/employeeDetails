package com.xinthe.employeeDetails.service;

import com.xinthe.employeeDetails.vos.EmployeeVO;
import com.xinthe.employeeDetails.vos.SalaryRange;

import java.util.List;

public interface EmployeeService {
    void updatePercentageByPlace(String place, Double percentage) throws Exception;
    List<EmployeeVO> getEmployeesByPlace(String place, Integer pageNo, Integer pageSize) throws Exception;
    SalaryRange getSalaryRangeByCompetency(String competency) throws Exception;
}
