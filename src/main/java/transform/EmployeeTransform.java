package transform;

import com.xinthe.employeeDetails.entity.Employee;
import com.xinthe.employeeDetails.vos.EmployeeVO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransform implements ObjectTransformer<Employee, EmployeeVO>{
    @Override
    public EmployeeVO toValueObject(Employee entity) {
        return EmployeeVO.builder()
                .employeeId(entity.getEmployeeId())
                .employeeName(entity.getEmployeeName())
                .businessUnit(entity.getBusinessUnit())
                .place(entity.getPlace())
                .supervisorId(entity.getSupervisorId())
                .salary(entity.getSalary()).build();
    }

    @Override
    public Employee toEntity(EmployeeVO valueObject) {
        return null;
    }
}
