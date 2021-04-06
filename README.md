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
Neste exemplo será configurado uma aplicação Spring Boot para utilizar o Redis como provedor de cache distribuído e a aplicação possui seu banco de dados, exemplificado na tecnologia do H2. Utilizando o Redis como provedor cache, o gerenciamento do cache não fica dentro da aplicação e sim no Redis, possibilitando que outras aplicações reaproveitem a mesma fonte de cache, caracterizando o cache como distribuído. 

## Redis
O Redis é uma solução open source para armazenamento de estrutura de dados em memória, o qual pode ser utilizada como banco de dados, cache ou message broker. Ele é uma boa solução para realizar cache distribuídos em aplicações Java, além de apresentar uma fácil integração através das dependências do spring-data e spring-data-redis utilizando a abstração de cache do Spring Boot. 

## Sequência Desenvolvimento deste Exemplo

- Utilizado como base para este projeto o star.spring.io, somente com a dependencia do Spring Web, as demais serão acrescentadas manualmente.
</br>
- Adicionado no POM a dependencia do Spring Cache
   ```
   <dependency> 
     <groupId>org.springframework.boot</groupId> 
     <artifactId>spring-boot-starter-cache</artifactId> 
   </dependency>
  
 - Adicionado a anotação @EnableCache
    ```
    @SpringBootApplication
    @EnableCaching
    public class AprendendoCacheApplication {

	    public static void main(String[] args) {
		    SpringApplication.run(AprendendoCacheApplication.class, args);
	    }
    }
   ```
- Adicionado a anotation @Cacheable para gravar os dados no cache
  Nesta primeira parte do exemplo, o @Cacheable foi utilizado em um controller:
  ```
  @RestController
  public class HelloController {

    @GetMapping("/hello")
    @Cacheable("helloCacheVar")
    public String hello(){
        System.out.println("Sem cache");
        return "Hello World";
    }
    ```
    Com esta configuração o cache já esta funcionando. O Spring utiliza um ConcurrentHashMap por debaixo dos panos para colocar na memoria RAM. 
A abstração de cache do Spring suporta uma ampla gama de bibliotecas de cache e é totalmente compatível com JSR-107 (JCache).
<br>
- Adicionado o @CacheEvict para limpar o cache
    ```
    @GetMapping("/cancel")
    @CacheEvict("helloCacheVar")
    public String cancel(){
        System.out.println("Limpando o cache");
        return "Cache Cancelado";
    }
    ```
- Adicionar o Redis no POM
    ```
    <dependency> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-data-redis</artifactId> 
    </dependency> 
    ```
- Configura o Redis no application.properties
    ```
    spring.cache.type=redis 
    spring.redis.host=localhost 
    spring.redis.port=6379 
- Roda um docker do Redis
    ```
    docker run -it --name redis -p 6379:6379 redis:5.0.3
    ```
- Para saber quais docker esta rodando 
    ```
    docker ps
    ```
- Para entrar no redis
    ```
    docker exec -it redis /bin/bash
    redis-cli
    ```
- Para mostrar o que tem dentro
    ```
    KEYS *
    ```
- Para ver o conteúdo da Key
    ```
    get "hello::SimpleKey []"
    ```
- Para apagar a Key
    ```
    del "hello::SimpleKey []"
    ```
Na segunda parte deste exemplo foi adicionado uma pequena API de exemplo e os comandos relativo ao cache foram colocados na Service:

```
    @Service
    public class StudentService {

        @Autowired
        private StudentRepository studentRepository;

        @Cacheable(cacheNames = "students")
        public List<Student> findAll(){
            System.out.println("Students sem cache");
            return studentRepository.findAll();
        }

        @Cacheable(cacheNames = "students")
        public Optional<Student> findById(Integer id){
            System.out.println("Students sem cache by Id");
            return studentRepository.findById(id);
        }


        @CachePut(cacheNames = "students", key="#id")
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
```
Para simular uma aplicação com cache distribuido, pode-se fazer uma cópia deste projeto e alterar a porta padrão no **application.properties**. Ao subir as duas aplicações, será possível verificar que ambas compartilharão o redis e terão acesso a um cache comum com o mesmo comportamento em ambas.
```
server.port: 8061
```
</br>
Referências:
</br>

- https://spring.io/guides/gs/caching/ 
- https://www.baeldung.com/spring-cache-tutorial 
- https://imasters.com.br/back-end/armazenamento-em-cache-de-spring-3-1-e-cacheable 
- https://reflectoring.io/spring-boot-cache/ 
- https://emmanuelneri.com.br/2019/04/30/cache-distribuido-com-redis-no-spring-boot/ 
- https://medium.com/dev-cave/redis-i-cache-distribu%C3%ADdo-34190dce037a 
- Pedro Cavalero Java Para Iniciantes    9:45 configura o Redis 
   https://www.youtube.com/watch?v=k58Dk5wXdvc 
- https://reflectoring.io/spring-boot-hazelcast/#embedded-cache-topology 

Referencia a outros tecnologias para armazenamento distribuido 
- https://www.infoq.com/br/news/2012/03/hazelcast-2.0 
- https://imasters.com.br/back-end/cache-distribuido-com-rest-e-ehcache-server-no-jboss-as 
- https://aws.amazon.com/pt/caching/ 
