package dev.pack.service.interfaces;

import dev.pack.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee data);
    List<Employee> createMultipleEmployee(List<Employee> dataMultiple);
    List<Employee> getAllEmployee();
}
