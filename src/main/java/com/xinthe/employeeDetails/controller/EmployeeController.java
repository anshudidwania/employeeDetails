package com.xinthe.employeeDetails.controller;

import com.xinthe.employeeDetails.exception.EmployeeException;
import com.xinthe.employeeDetails.service.EmployeeService;
import com.xinthe.employeeDetails.vos.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PutMapping(path = "/place/{place}/salary/{percentage}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePercentageByPlace(@PathVariable String place, @PathVariable Double percentage){
        try {
            employeeService.updatePercentageByPlace(place, percentage);
            return buildResponse("success", null);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return buildResponse(null, e);
        }
    }
    @GetMapping(path = "/place/{place}/pageNo/{pageNo}/pageSize/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO getEmployeesByPlace(@PathVariable String place, @PathVariable Integer pageNo, @PathVariable Integer pageSize){
        try {
            return buildResponse(employeeService.getEmployeesByPlace(place, pageNo, pageSize), null);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return buildResponse(null, e);
        }
    }
    @GetMapping(path = "/competency/{competency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO getSalaryRangeByCompetency(@PathVariable String competency){
        try {
            return buildResponse(employeeService.getSalaryRangeByCompetency(competency), null);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return buildResponse(null, e);
        }
    }
    private ResponseVO buildResponse(Object data, Exception error) {
        if (isNull(error)) {
            return ResponseVO.builder()
                    .success(true)
                    .data(data).build();
        }else{
            EmployeeException ex = EmployeeException.builder()
                    .errorCode("E500")
                    .description(error.getMessage() + error.getLocalizedMessage())
                    .error(error.fillInStackTrace()).build();
            return ResponseVO.builder()
                    .success(false)
                    .error(ex).build();
        }
    }
}
