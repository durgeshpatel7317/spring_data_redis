package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    private final StudentRepository repository;
    private final RedisUtilService redisUtilService;

    @Autowired
    public StudentsService(StudentRepository repository, RedisUtilService redisUtilService) {
        this.repository = repository;
        this.redisUtilService = redisUtilService;
    }

    public Student addNewStudent(Student student) {
        Student savedStudent = repository.save(student);
        // Saving the new student to redis so that it can be fetched in future
        redisUtilService.save(savedStudent);
        return savedStudent;
    }

    // find existing record
    public Student findStudent(Long studentId) {
        Student student = redisUtilService.findById(studentId);
        if (student != null) {
            return student;
        }
        return repository.findById(studentId).get();
    }

    // deleting existing record
    public String deleteStudent(Long studentId) {
        repository.deleteById(studentId);
        redisUtilService.deleteStudent(studentId);
        return "record deleted successfully";
    }
}
