# Spring Cache
Aplicação para estudo do Spring Cache
## O que é o armazenamento em cache? 

Na área de computação, um cache é uma camada de armazenamento físico de dados de alta velocidade que guarda um subconjunto de dados, geralmente temporário por natureza, para que futuras solicitações referentes a esses dados sejam atendidas de modo mais rápido do que é possível fazer ao acessar o local de armazenamento principal de dados. O armazenamento em cache permite reutilizar com eficiência dados recuperados ou computados anteriormente. 
 
## Como funciona o armazenamento em cache? 

Os dados em um cache geralmente são armazenados no hardware de acesso rápido, como uma Random-access memory (RAM – Memória de acesso aleatório), e também podem ser usados em paralelo com um componente de software. O principal objetivo de um cache é aumentar a performance da recuperação de dados ao reduzir a necessidade de acessar a camada subjacente mais lenta de armazenamento. 
A substituição de capacidade por velocidade geralmente faz com que um cache armazene um subconjunto de dados de modo temporário, em comparação com bancos de dados, cujos dados são, de modo geral, completos e duráveis. <br>
Cache é uma das abordagens para otimizar acesso à dados dos sistemas, onde evitamos requisições repetitivas nas fontes originais dos dados, que geralmente são grandes estruturas, complexas e nem sempre performáticas, assim com cache, passamos a consultar locais mais otimizados, que provêm acessos rápidos através de chaves. 

## JAVA
Há diversas tecnologias de cache para utilizarmos nas aplicações Java, como: EHCache, Redis, Infinispan, Caffeine, etc, porém quando começamos a se preocupar com escalabilidade das nossas aplicações, consequentemente em aumentar o número de instâncias simultâneas das nossas aplicações, precisamos pensar em provedores que nos forneçam a possibilidade de cache distribuído, de forma que as informações armazenadas em cache possam ser compartilhada entre as instâncias, assim aprimorando o uso dos cache entre as aplicações, além de evitar problemas de validade dos caches entre as aplicações concorrentes. 

## Objetivo deste Repositório
Neste exemplo será configurado uma aplicação Spring Boot para utilizar o Redis como provedor de cache distribuído, assim a aplicação possui seu banco de dados, exemplificado na tecnologia do H2 e utiliza o Redis como provedor cache, dessa forma, o gerenciamento do cache não fica dentro da aplicação e sim no Redis, possibilitando que outras aplicações reaproveitem a mesma fonte de cache, caracterizando o cache como distribuído. 

## Redis
o Redis, que é uma solução open source para armazenamento de estrutura de dados em memória, o qual pode ser utilizada como banco de dados, cache ou message broker. Ele é uma boa solução para realizar cache distribuídos em aplicações Java, além de apresentar uma fácil integração através das dependências do spring-data e spring-data-redis utilizando a abstração de cache do Spring Boot. 

## Sequência Construção

- Utilizado como base para este projeto o star.spring.io, somente com a dependencia do Spring Web, as demais serão acrescentadas manualmente

- Adicionado dependencia do Spring Cache
```<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-cache</artifactId> 
    </dependency>
 ```   
 
 - Adiciona @EnableCache
 ```
 @SpringBootApplication
@EnableCaching
public class AprendendoCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendendoCacheApplication.class, args);
	}
}
```
- Adiciona @Cacheable 
