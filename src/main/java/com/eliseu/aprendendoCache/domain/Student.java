package com.eliseu.aprendendoCache.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String CACHE_NAME = "Student";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String nome;

    Long idade;

    public Student() {
    }

    public Student(String nome, Long idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(nome, student.nome) &&
                Objects.equals(idade, student.idade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade);
    }
}
