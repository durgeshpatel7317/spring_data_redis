package com.example.demo.controllers;

import com.example.demo.model.Student;
import com.example.demo.services.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiController {

    private final StudentsService service;

    @Autowired
    public ApiController(StudentsService service) {
        this.service = service;
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        log.info("Here is the value of student {} ", student);
        return new ResponseEntity<>(service.addNewStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> retriveAllStudents(@PathVariable Long id) {
        return new ResponseEntity<>(service.findStudent(id), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteStudent(id), HttpStatus.OK);
    }
}
