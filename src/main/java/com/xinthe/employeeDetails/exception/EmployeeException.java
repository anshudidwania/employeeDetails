package com.xinthe.employeeDetails.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class EmployeeException extends Exception{
    private String errorCode;
    private String description;
    private Throwable error;
}