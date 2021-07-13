# API Novo Simulador Parametrização

Este projeto tem como objetivo expor Api's para cadastro de parâmetros do novo simulador.

### Pré-requisitos

Este projeto tem como pré-requisitos e stack as seguintes tecnologias:

- Apache Maven
- Java 11
- Mongo DB
- IDE com suporte a Lombok

O idioma utilizado na codificação deve ser o português juntamente com o ingles (Classes e atributos)

### Stack Tecnológica

Este projeto foi construido e opera com as seguintes stacks tecnológicas:

- Openshift
- Spring Boot
- Spring Web
- Spring Data JPA
- Swagger para documentação das API's

### Deploy

O deploy da aplicação será realizado no OpenShift e precisará receber as seguintes variaveis de ambiente:

	    GRAYLOG_HOST
        GRAYLOG_PORT
        MONGODB_HOST
        MONGODB_DATABASE
        MONGODB_OPTIONS

### Teste integrado

### Sonar

Para executar a analise do sonar rodar o comando maven abaixo:

        mvn -Dsonar.host.url=http://10.35.108.190 clean install sonar:sonar

Caso tenha um sonar local é só não enviar nada pois o default é http://localhost:9000

### Testes de Componente

Para rodar os testes de componente localmente via maven utilizar o comando abaixo alterando o endereço da aplicação caso necessario.

        mvn -DURL_TESTE_COMPONENTE=http://localhost:8080/api/ -f tests/component-tests/pom.xml clean install

Para incluir o teste de componente como modulo no intellij é só adicionar o pom como maven project no intellij