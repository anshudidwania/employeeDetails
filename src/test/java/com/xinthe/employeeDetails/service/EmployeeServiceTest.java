package com.xinthe.employeeDetails.service;

import com.xinthe.employeeDetails.dao.CompetencyRepository;
import com.xinthe.employeeDetails.dao.EmployeeRepository;
import com.xinthe.employeeDetails.entity.Competency;
import com.xinthe.employeeDetails.entity.Employee;
import com.xinthe.employeeDetails.vos.EmployeeVO;
import com.xinthe.employeeDetails.vos.SalaryRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import transform.EmployeeTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CompetencyRepository competencyRepository;
    @Mock
    private EmployeeTransform employeeTransform;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updatePercentageByPlace_greaterThan55_Exception() {
        assertThrows(Exception.class, () -> employeeService.updatePercentageByPlace("a", 56.0), "Computed bill service throws exception");
    }
    @Test
    public void updatePercentageByPlace_smallerThan55() throws Exception {
        List<Employee> empLs = new ArrayList<>();
        Employee emp = new Employee();
        emp.setEmployeeId(123);
        emp.setSalary(2000.0);
        empLs.add(emp);
        when(employeeRepository.getEmployeeByPlace(anyString())).thenReturn(empLs);
        when(employeeRepository.saveAll(anyIterable())).thenReturn(empLs);
        employeeService.updatePercentageByPlace("a", 20.0);
        assertEquals(emp.getSalary(), 2400.0);
    }

    @Test
    public void getEmployeesByPlace_success() throws Exception {
        List<Employee> empLs = new ArrayList<>();
        Employee emp = new Employee();
        emp.setEmployeeId(123);
        empLs.add(emp);
        Page<Employee> page = new PageImpl<>(empLs);
        when(employeeRepository.findAll(any(), (Pageable) any())).thenReturn(page);
        List<EmployeeVO> resEmpLs = employeeService.getEmployeesByPlace("a", 0, 2);
        assertEquals(resEmpLs.size(),empLs.size());
    }

    @Test
    public void getSalaryRangeByCompetency_Exception() throws Exception {
        when(competencyRepository.findOne(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> employeeService.getSalaryRangeByCompetency("a"));
    }
    @Test
    public void getSalaryRangeByCompetency_success() throws Exception {
        SalaryRange s = new SalaryRange();
        s.setMaxSalary(123.0);
        Competency comp = new Competency();
        comp.setCompetencieId(123);
        comp.setCompetencieName("abc");
        Example<Competency> example = Example.of(comp, ExampleMatcher.matching());
        when(competencyRepository.findOne(any())).thenReturn(Optional.of(comp));
        when(employeeRepository.getSalaryRangeByCompetency(anyInt())).thenReturn(s);
        SalaryRange rs = employeeService.getSalaryRangeByCompetency("abc");
        assertEquals(123.0,rs.getMaxSalary());
    }
}
