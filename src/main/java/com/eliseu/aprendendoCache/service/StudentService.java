package com.eliseu.aprendendoCache.service;

import com.eliseu.aprendendoCache.domain.Student;
import com.eliseu.aprendendoCache.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable(value = "students")
    public List<Student> findAll() {
        System.out.println("Find All Students");
        return studentRepository.findAll();
    }

    @Cacheable(value = "students")
    public Optional<Student> findById(Integer id) {
        System.out.println("Find By Id Students");
        return studentRepository.findById(id);
    }

    @CacheEvict(value = "students", allEntries = true)
    public Student update(Integer id, Student obj) {
        Student entity = studentRepository.getOne(id);
        entity.setNome(obj.getNome());
        entity.setIdade(obj.getIdade());
        System.out.println("Put update Student");
        return studentRepository.save(entity);
    }

    @CacheEvict(value = "students", allEntries = true)
    public void delete(Integer id) {
        System.out.println("Delete Student");
        studentRepository.deleteById(id);
    }

    @CacheEvict(value = "students", allEntries = true)
    public Student incluir(Student alunoInformado) {
        System.out.println("Post incluir Student");
        return studentRepository.save(alunoInformado);
    }

}
