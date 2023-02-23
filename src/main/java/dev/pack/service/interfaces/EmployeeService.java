package dev.pack.service.interfaces;

import dev.pack.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee data);
    Iterable<Employee> createBatch (Iterable<Employee> employees);
    List<Employee> getAllEmployee();
    void removeById(Integer id);
    List<Employee> findEmployeeByName(String name);
}
