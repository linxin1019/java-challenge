package jp.co.axa.apidemo.controllers;

import io.swagger.annotations.*;
import jp.co.axa.apidemo.common.PageInfo;
import jp.co.axa.apidemo.common.RequestResult;
import jp.co.axa.apidemo.common.ResultCodeEnum;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@Api(tags = "api for employees management")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "get all employees with pagination")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "token", value = "authentication token. provided when login. use '123456' for test", required = true, paramType = "header"))
    @PostMapping("/getEmployees")
    public RequestResult getEmployees(@RequestBody @ApiParam(value = "page info", required = true) PageInfo pageInfo) {
        Page<Employee> employees;
        employees = employeeService.retrieveEmployees(PageRequest.of(pageInfo.getPage(), pageInfo.getSize()));
        if (employees.isEmpty()) {
            log.info("getEmployees result empty. pageInfo: {}", pageInfo);
            return new RequestResult(ResultCodeEnum.EMPTY_RESULT, null);
        } else {
            return new RequestResult(employees);
        }
    }

    @ApiOperation(value = "get the employee by Id")
    @ApiImplicitParams({@ApiImplicitParam(name = "employeeId"
        , value = "the Id of the employee you want to retrieve"
        , required = true
        , dataType = "long"
        , paramType = "path")
    , @ApiImplicitParam(name = "token", value = "authentication token. provided when login. use '123456' for test", required = true, paramType = "header")})
    @GetMapping("/employees/{employeeId}")
    public RequestResult getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        // get the existed employee by id
        Employee employee = employeeService.getEmployee(employeeId);
        if (null == employee) {
            log.info("Employee not exist. Id: {}", employeeId);
            return new RequestResult(ResultCodeEnum.EMPTY_RESULT, null);
        } else {
            return new RequestResult(employee);
        }
    }

    @ApiOperation(value = "create an employee")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "token", value = "authentication token. provided when login. use '123456' for test", required = true, paramType = "header"))
    @PostMapping("/employees")
    public RequestResult saveEmployee(@RequestBody @ApiParam(value = "employee's information you want to create"
            , required = true) Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);
        log.info("Employee Saved Successfully. Id: {}", savedEmployee.getId());
        // construct the return object
        HashMap map = new HashMap();
        map.put("id", savedEmployee.getId());
        return new RequestResult(map);
    }

    @ApiOperation(value = "delete an employee by Id")
    @ApiImplicitParams({@ApiImplicitParam(name = "employeeId"
        , value = "the Id of the employee you want to delete"
        , required = true
        , dataType = "long"
        , paramType = "path")
     , @ApiImplicitParam(name = "token", value = "authentication token. provided when login. use '123456' for test", required = true, paramType = "header")})
    @DeleteMapping("/employees/{employeeId}")
    public RequestResult deleteEmployee(@PathVariable(name="employeeId")Long employeeId) {
        // get the existed employee by id
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp == null) {
            // construct the return object
            HashMap map = new HashMap();
            map.put("id", employeeId);
            log.info("Employee to be deleted not exist. Id: {}", employeeId);
            return new RequestResult(ResultCodeEnum.DELETE_TARGET_NOT_EXIST, map);
        } else {
            employeeService.deleteEmployee(employeeId);
            log.info("Employee Deleted Successfully. Id: {}", employeeId);
            return new RequestResult(null);
        }
    }

    @ApiOperation(value = "update an employee by Id")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "token", value = "authentication token. provided when login. use '123456' for test", required = true, paramType = "header"))
    @PutMapping("/employees")
    public RequestResult updateEmployee (
            @RequestBody @ApiParam(value = "employee's new information you want to update"
                    , required = true) Employee employee) {
        // get the existed employee by id
        Employee emp = employeeService.getEmployee(employee.getId());
        if (emp == null) {
            // construct the return object
            HashMap map = new HashMap();
            map.put("id", employee.getId());
            log.info("Employee to be updated not exist. Id: {}", employee.getId());
            return new RequestResult(ResultCodeEnum.UPDATE_TARGET_NOT_EXIST, map);
        } else {
            log.info("UpdateEmployee before: {}", emp);
            employeeService.updateEmployee(employee);
            log.info("Employee Updated Successfully. Id: {}", emp.getId());
            return new RequestResult(null);
        }
    }

}
