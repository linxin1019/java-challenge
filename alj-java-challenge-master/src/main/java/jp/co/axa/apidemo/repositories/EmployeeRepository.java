package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findAll(Pageable page);
}
