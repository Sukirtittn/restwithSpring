package com.spring.rest.springrest.controllers;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.rest.springrest.entities.User;
import com.spring.rest.springrest.exceptions.UserNotFoundException;
import com.spring.rest.springrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/")
    String helloWorld(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println("Hello World");
        System.out.println(locale.getLanguage());
        return messageSource.getMessage("greeting.message", null, locale);
    }

    @GetMapping("/users")
    List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null)
            throw new UserNotFoundException("User Not Found!!");
        return user;
    }

    @PostMapping("/users")
    ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User Not Found!!!");
        }
        userService.deleteUser(user);
    }

/*    @GetMapping("/filter")
    MappingJacksonValue getUserExceptAge(){
        User user=new User("suki",25);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter=SimpleBeanPropertyFilter.filterOutAllExcept("age");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("myFilter",simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }*/
}