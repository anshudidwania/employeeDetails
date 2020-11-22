package com.xinthe.employeeDetails.service;

import com.xinthe.employeeDetails.dao.CompetencyRepository;
import com.xinthe.employeeDetails.dao.EmployeeRepository;
import com.xinthe.employeeDetails.entity.Competency;
import com.xinthe.employeeDetails.entity.Employee;
import com.xinthe.employeeDetails.exception.EmployeeException;
import com.xinthe.employeeDetails.vos.EmployeeVO;
import com.xinthe.employeeDetails.vos.SalaryRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import transform.EmployeeTransform;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompetencyRepository competencyRepository;
    @Autowired
    private EmployeeTransform employeeTransform;

    @Transactional
    public void updatePercentageByPlace(String place, Double percentage) throws Exception {
        if(percentage > 55) throw new EmployeeException("E055", "Percentage to high", null);
        List<Employee> empList = employeeRepository.getEmployeeByPlace(place);
        if(!Objects.isNull(empList)){
            empList.forEach(employee -> employee.setSalary(employee.getSalary() * (1+(percentage/100))));
        }
        employeeRepository.saveAll(empList);
    }
    @Transactional
    public List<EmployeeVO> getEmployeesByPlace(String place, Integer pageNo, Integer pageSize) throws Exception {
        Employee emp = new Employee();
        emp.setPlace(place);
        Example<Employee> example = Example.of(emp, ExampleMatcher.matching());
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> pagedResult = employeeRepository.findAll(example, paging);
        return pagedResult.getContent().stream().map(e -> employeeTransform.toValueObject(e)).collect(Collectors.toList());
    }
    @Transactional
    public SalaryRange getSalaryRangeByCompetency(String competency) throws Exception{
        Competency comp = new Competency();
        comp.setCompetencieName(competency);
        Example<Competency> example = Example.of(comp, ExampleMatcher.matching());
        Optional<Competency> comOpt = competencyRepository.findOne(example);
        comp = comOpt.get();
        return employeeRepository.getSalaryRangeByCompetency(comp.getCompetencieId());
    }
}
