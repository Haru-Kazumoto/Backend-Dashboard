package dev.pack.controller;

import dev.pack.dto.EmployeeDTO;
import dev.pack.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import dev.pack.model.Employee;
import dev.pack.service.interfaces.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    private final ResponseData<Employee> dataResponse = new ResponseData<>();

    private final EmployeeService service;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createData(
            @RequestBody @Valid EmployeeDTO dto) {
        try{
            Employee employee = modelMapper.map(dto, Employee.class);

            dataResponse.setStatus(HttpStatus.CREATED);
            dataResponse.setPayload(service.createEmployee(employee));

            return ResponseEntity.accepted().body(dataResponse);
        } catch (ResponseStatusException exception){
            Map<String, Object> dataMessage = new HashMap<>();
            List<String> dataError = List.of(
                    "Duplicate unique data."
            );

            dataMessage.put("Status", HttpStatus.CONFLICT);
            dataMessage.put("Message", dataError);

            return ResponseEntity.status(exception.getStatusCode()).body(dataMessage);
        }
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ResponseData<List<Employee>>> findAll(){
        ResponseData<List<Employee>> data = new ResponseData<>();

        data.setStatus(HttpStatus.OK);
        data.setPayload(service.getAllEmployee());

        return ResponseEntity.ok(data);
    }
}
