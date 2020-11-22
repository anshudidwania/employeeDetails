package com.xinthe.employeeDetails.vos;

import com.xinthe.employeeDetails.exception.EmployeeException;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseVO {
    private boolean success=true;
    private EmployeeException error;
    private Object data;
}
