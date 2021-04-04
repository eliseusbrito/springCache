package com.eliseu.aprendendoCache.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Cacheable("helloCacheVar")
    public String hello(){
        System.out.println("Sem cache");
        return "Hello World";
    }

    @GetMapping("/cancel")
    @CacheEvict("helloCacheVar")
    public String cancel(){
        System.out.println("Limpando o cache");
        return "Cache Cancelado";
    }

}
