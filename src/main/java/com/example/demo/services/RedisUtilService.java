package com.example.demo.services;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtilService {

    private final RedisTemplate<Long, Student> redisTemplate;

    @Autowired
    public RedisUtilService(RedisTemplate<Long, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(Student student) {
        redisTemplate.opsForValue().set(student.getStudentId(), student, 10, TimeUnit.MINUTES);
    }

    public Student findById(Long id) {
        return redisTemplate.opsForValue().get(id);
    }

    public Student deleteStudent(Long id) {
        return redisTemplate.opsForValue().getAndDelete(id);
    }
}
