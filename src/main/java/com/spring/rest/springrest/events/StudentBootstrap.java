package com.spring.rest.springrest.events;

import com.spring.rest.springrest.entities.Student;
import com.spring.rest.springrest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class StudentBootstrap {
    @Autowired
    StudentRepository studentRepository;
    @EventListener(ContextRefreshedEvent.class)
    void setUp(){

        if(!studentRepository.findAll().iterator().hasNext()) {
            IntStream.range(0, 10).forEach(e -> {
                Student student=new Student();
                student.setId(e);
                student.setName("name" + e);
                student.setStandard("standard"+e);
                student.setAge(20+e);
                student.setCity("city"+e);
                student.setPercentage(90f+e);
                studentRepository.save(student);
            });
        }
    }
}
