package jp.co.axa.apidemo.controllers;

import io.swagger.annotations.*;
import jp.co.axa.apidemo.common.RequestResult;
import jp.co.axa.apidemo.common.ResultCodeEnum;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "api for employees management")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "get all employees")
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

    @ApiOperation(value = "get the employee by Id")
    @ApiImplicitParams(@ApiImplicitParam(name = "employeeId"
        , value = "the Id of the employee you want to retrieve"
        , required = true
        , dataType = "long"
        , paramType = "path"))
    @GetMapping("/employees/{employeeId}")
    public RequestResult getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (null == employee) {
            return new RequestResult(ResultCodeEnum.EMPTY_RESULT, null);
        } else {
            return new RequestResult(employee);
        }
    }

    @ApiOperation(value = "create an employee")
    @PostMapping("/employees")
    public RequestResult saveEmployee(@RequestBody @ApiParam(value = "employee's information you want to create"
            , required = true) Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
        return new RequestResult(null);
    }

    @ApiOperation(value = "delete an employee by Id")
    @ApiImplicitParams(@ApiImplicitParam(name = "employeeId"
        , value = "the Id of the employee you want to delete"
        , required = true
        , dataType = "long"
        , paramType = "path"))
    @DeleteMapping("/employees/{employeeId}")
    public RequestResult deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
        return new RequestResult(null);
    }

    @ApiOperation(value = "update an employee by Id")
    @PutMapping("/employees/{employeeId}")
    public RequestResult updateEmployee (
            @RequestBody @ApiParam(value = "employee's new information you want to update"
                    , required = true) Employee employee) {
        Employee emp = employeeService.getEmployee(employee.getId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ");
        stringBuilder.append(employee.getId());
        if (emp == null) {
            return new RequestResult(ResultCodeEnum.UPDATE_TARGET_NOT_EXIST, stringBuilder);
        } else {
            employeeService.updateEmployee(employee);
            return new RequestResult(null);
        }
    }

}
