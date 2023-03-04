package dev.pack.controller;

import dev.pack.dto.EmployeeDTO;
import dev.pack.dto.ResponseData;
import dev.pack.model.enumeration.JobRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import dev.pack.model.Employee;
import dev.pack.service.interfaces.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/employee")
@CrossOrigin(originPatterns = "http://localhost:3001")
public class EmployeeController {

    public ResponseData<Employee> dataResponse = new ResponseData<>();
    Map<String, Object> messageError = new HashMap<>();
    public Map<String, String> messageResult = new HashMap<>();

    private final EmployeeService service;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createData(@RequestBody @Valid EmployeeDTO dto){
        try{
            Employee employee = modelMapper.map(dto, Employee.class);

            dataResponse.setStatus(HttpStatus.CREATED);
            dataResponse.setPayload(service.createEmployee(employee));

            return ResponseEntity.accepted().body(dataResponse);
        } catch(ResponseStatusException ex){
            List<String> dataError = List.of(
                    "Duplicate Data"
            );

            messageError.put("Status", HttpStatus.CONFLICT);
            messageError.put("Message", dataError);

            return ResponseEntity.status(ex.getStatusCode()).body(messageError);
        }
    }

    @PostMapping(path = "/create-all")
    public ResponseEntity<?> createBatch(@RequestBody @Valid Employee[] employees){
        try{
            service.createBatch(Arrays.asList(employees));
            return ResponseEntity.accepted().body(employees);
        } catch (ResponseStatusException exception){
            List<String> dataError = List.of(
                    "Duplicate unique data."
            );

            messageError.put("Status", HttpStatus.CONFLICT);
            messageError.put("Message", dataError);

            return ResponseEntity.status(exception.getStatusCode()).body(messageError);
        }
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<?> findAll(HttpServletRequest request){
        String clientPort = request.getHeader("X-Client-Port");
        log.info("Request received from client on port {}.", clientPort);
        return ResponseEntity.ok(service.getAllEmployee());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteRecordById(@PathVariable Integer id){
        service.removeById(id);
        messageResult.put("result", String.format("Data with id %s has deleted", id));
        return ResponseEntity.accepted().body(messageResult);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Integer id,
            @RequestBody @Valid Employee employee){
        try{
            dataResponse.setStatus(HttpStatus.OK);
            dataResponse.setMessage(List.of(String.format("Data with id %s has updated", id)));
            dataResponse.setPayload(service.updateById(employee));

            return ResponseEntity.accepted().body(dataResponse);
        } catch(ResponseStatusException ex){
            List<String> dataError = List.of(
                    "Duplicate Data"
            );

            messageError.put("Status", HttpStatus.CONFLICT);
            messageError.put("Message", dataError);

            return ResponseEntity.status(ex.getStatusCode()).body(messageError);
        }
    }

    @GetMapping(path = "/count-roles")
    public Map<String, Integer> getJobRoles(){
        Map<String, Integer> jobRoles = new HashMap<>();
        List<Object[]> employeeCountByRole = service.getEmployeeCountRole();

        for(Object[] result : employeeCountByRole) {
            JobRole role = (JobRole) result[0];
            Long count = (Long) result[1];

            jobRoles.put(role.toString(), count.intValue());
        }

        return jobRoles;
    }
}
