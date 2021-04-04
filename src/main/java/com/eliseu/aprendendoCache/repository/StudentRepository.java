package com.eliseu.aprendendoCache.repository;

import com.eliseu.aprendendoCache.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
