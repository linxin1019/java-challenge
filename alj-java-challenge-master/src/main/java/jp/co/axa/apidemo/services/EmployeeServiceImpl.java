package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Cacheable(value = "employee", key = "#employeeId", unless="#result == null")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.orElse(null);
    }

    public Employee saveEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @CacheEvict(value = "employee", key = "#employeeId")
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    @CachePut(value = "employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }
}