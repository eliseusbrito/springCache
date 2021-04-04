package com.eliseu.aprendendoCache.config;

import com.eliseu.aprendendoCache.domain.Student;
import com.eliseu.aprendendoCache.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @CacheEvict(value={"students","helloCacheVar"}, allEntries=true)
    @Override
    public void run(String... args) throws Exception {
        Student u1 = new Student("Maria Brown", 20L);
        Student u2 = new Student("Alex Green", 25L);
        studentRepository.saveAll(Arrays.asList(u1, u2));
    }

}
