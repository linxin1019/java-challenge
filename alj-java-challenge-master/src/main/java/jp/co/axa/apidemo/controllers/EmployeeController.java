package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.common.RequestResult;
import jp.co.axa.apidemo.common.ResultCodeEnum;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public RequestResult getEmployees() {
        List<Employee> employees;
        employees = employeeService.retrieveEmployees();
        if (employees.isEmpty()) {
            return new RequestResult(ResultCodeEnum.EMPTY_RESULT, employees);
        } else {
            return new RequestResult(employees);
        }
    }

    @GetMapping("/employees/{employeeId}")
    public RequestResult getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (null == employee) {
            return new RequestResult(ResultCodeEnum.EMPTY_RESULT, null);
        } else {
            return new RequestResult(employee);
        }
    }

    @PostMapping("/employees")
    public RequestResult saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
        return new RequestResult(null);
    }

    @DeleteMapping("/employees/{employeeId}")
    public RequestResult deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
        return new RequestResult(null);
    }

    @PutMapping("/employees/{employeeId}")
    public RequestResult updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }
        return new RequestResult(null);
    }

}
