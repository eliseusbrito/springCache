package com.eliseu.aprendendoCache.controller;

import com.eliseu.aprendendoCache.domain.Student;
import com.eliseu.aprendendoCache.repository.StudentRepository;
import com.eliseu.aprendendoCache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> list = studentService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Integer id){
        Optional<Student> obj = studentService.findById(id);
        return ResponseEntity.ok(obj.get());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student obj) {
        obj = studentService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Student> incluir(@RequestBody Student obj){
        obj = studentService.incluir(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
}
