package com.eliseu.aprendendoCache.service;

import com.eliseu.aprendendoCache.domain.Student;
import com.eliseu.aprendendoCache.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable(cacheNames = "students")
//    @Cacheable(cacheNames = Student.CACHE_NAME, key="#root.method.name")
    public List<Student> findAll(){
        System.out.println("Students sem cache");
        return studentRepository.findAll();
    }

    @Cacheable(cacheNames = "students")
//    @Cacheable(cacheNames = "students", key="#id")
//    @Cacheable(cacheNames = Student.CACHE_NAME, key="#root.method.name")
    public Optional<Student> findById(Integer id){
        System.out.println("Students sem cache by Id");
        return studentRepository.findById(id);
    }


    @CachePut(cacheNames = "students", key="#id")
//    @CachePut(cacheNames = Student.CACHE_NAME, key="#Student.getId()")
//    @CachePut(cacheNames = Student.CACHE_NAME, key="#id")
//    @CachePut(value = "students", key="#student.id")
    public Student update(Integer id, Student obj) {
        Student entity = studentRepository.getOne(id);
        entity.setNome(obj.getNome());
        entity.setIdade(obj.getIdade());
        return studentRepository.save(entity);
    }

    @CacheEvict(value="students", allEntries=true)
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @CacheEvict(value="students", allEntries=true)
    public Student incluir(Student alunoInformado) {
       return studentRepository.save(alunoInformado);
    }

}
