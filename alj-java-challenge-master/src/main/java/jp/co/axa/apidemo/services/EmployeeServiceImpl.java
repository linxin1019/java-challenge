package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;

    public Page<Employee> retrieveEmployees(PageRequest pageRequest) {
        Page<Employee> employees = employeeRepository.findAll(pageRequest);
        log.info("retrieveEmployees result size: {} ", employees.getSize());
        return employees;
    }

    @Cacheable(value = "employee", key = "#employeeId", unless="#result == null")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        log.info("getEmployee result: {}", optEmp);
        return optEmp.orElse(null);
    }

    public Employee saveEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("saveEmployee result: {}", savedEmployee);
        return savedEmployee;
    }

    @CacheEvict(value = "employee", key = "#employeeId")
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        log.info("deleteEmployee id: {}", employeeId);
    }

    @CachePut(value = "employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("updateEmployee after: {}", updatedEmployee);
        return updatedEmployee;
    }
}