package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EmployeeService {

    public Page<Employee> retrieveEmployees(PageRequest pageRequest);

    public Employee getEmployee(Long employeeId);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(Long employeeId);

    public Employee updateEmployee(Employee employee);
}