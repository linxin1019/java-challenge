package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * for employee management
 */
public interface EmployeeService {

    /**
     * get employees with pagination
     * @param pageRequest
     * @return
     */
    public Page<Employee> retrieveEmployees(PageRequest pageRequest);

    /**
     * get employee by id
     * @param employeeId
     * @return
     */
    public Employee getEmployee(Long employeeId);

    /**
     * create a new employee
     * @param employee
     * @return
     */
    public Employee saveEmployee(Employee employee);

    /**
     * delete a employee by id
     * @param employeeId
     */
    public void deleteEmployee(Long employeeId);

    /**
     * update an employee
     * @param employee
     * @return
     */
    public Employee updateEmployee(Employee employee);
}