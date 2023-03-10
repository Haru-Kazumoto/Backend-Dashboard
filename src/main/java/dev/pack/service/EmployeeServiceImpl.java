package dev.pack.service;

import dev.pack.model.Employee;
import dev.pack.repository.EmployeeRepository;
import dev.pack.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;

    @Override
    public Employee createEmployee(Employee employee) throws DataIntegrityViolationException {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,exception.getMessage());
        }
    }

    @Override
    public Iterable<Employee> createBatch(Iterable<Employee> employees) {
        try{
            return repository.saveAll(employees);
        } catch(DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    public Employee updateById(Employee employee) {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,exception.getMessage());
        }
    }

    @Override
    public void removeById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        Optional<List<Employee>> data = Optional.ofNullable(repository.findByNameContains(name));
        if(data.isPresent()){
            return repository.findByNameContains(name);
        }
        return List.of();
    }

    @Override
    public List<Object[]> getEmployeeCountRole() {
        return repository.getEmployeeCountByRole();
    }
}
