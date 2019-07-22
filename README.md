# TESTE TCS SANTANDER

### Teste Desenvolvedor JAVA Marcio Lamego
Como cenario dado pelo enunciado do teste foi construido a seguinte arquitetura:

* Springboot 
* Eureka Server / Client - para gerenciar o loadbalance
* JPA - HSQL embbded in memory
* JSON - uma api produzindo JSON para o a funcionaliadde de autocomplete
* React js

### Guides
Para iniciar a aplicação deve-se

* mvn install [-DskipTests]
* mvn spring-boot:run
* cd teste-tcs-web
* npm install
* npm start
