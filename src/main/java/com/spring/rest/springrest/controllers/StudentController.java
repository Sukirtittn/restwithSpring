package com.spring.rest.springrest.controllers;

import com.spring.rest.springrest.entities.Student;
import com.spring.rest.springrest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/allUsers")
    List<Student> getAllUsers(){
        return  studentService.getAllStudent();
    }
}