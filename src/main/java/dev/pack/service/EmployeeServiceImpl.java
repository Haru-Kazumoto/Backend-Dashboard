package dev.pack.service;

import dev.pack.model.Employee;
import dev.pack.repository.EmployeeRepository;
import dev.pack.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;

    @Override
    public Employee createEmployee(Employee data) {
        try{
            return repository.save(data);
        } catch (DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage());
        }
    }

    @Override
    public List<Employee> createMultipleEmployee(List<Employee> dataMultiple) {
        return repository.saveAll(dataMultiple);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }
}
