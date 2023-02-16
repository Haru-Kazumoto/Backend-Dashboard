package dev.pack.service.interfaces;

import dev.pack.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee createEmployee(Employee data);
    Iterable<Employee> createBatch (Iterable<Employee> employees);
    List<Employee> getAllEmployee();
    void removeById(Integer id);
}
