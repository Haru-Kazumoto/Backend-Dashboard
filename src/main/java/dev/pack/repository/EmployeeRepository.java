package dev.pack.repository;

import dev.pack.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    Employee findByNumberEmployee(String number);
    List<Employee> findByNameContains(String name);
}
