# Aplicação
Foi utilizada a linguagem Java com o framework Spring pela facilidade em criar e manter o projeto. O banco de dados é o H2 Database, pela simplicidade do uso, adequado para o teste, que não exige recursos avançados.

# Como Utilizar
O banco de dados é em memória então, não precisaremos configurar nenhum ambiente de dados, porém será necessário o Java 8 e Maven (versão utilizada 3.5.0)
Tecnologias utilizadas

# Tecnologias Utilizadas
Spring Boot

Hibernate

Java 8

Rest

H2 Database

# Instruções para execução
1 - Clone o projeto https://github.com/alanlcardoso/xy-inc.git

2 - Entre na pasta xy-inc através de um terminal e execute os comandos abaixo:

3 - A aplicação estará rodando na porta 8081 no servidor local.

$ mvn clean package

$ java -jar target/xyinc.jar

# Fazendo requisições
Listar todos

GET http://localhost:8081/poi

Salvar

POST http://localhost:8081/poi
{
	"name" : "Churrascaria",
	"coordinateX" : 28,
	"coordinateY" : 2
}

Buscar próximos

GET http://localhost:8081/poi/nearby/20/10/10


# Testes
A aplicação possui testes unitários, testes de repositório, serviço e negócio. Para rodar os testes basta executar o seguinte comando:

$ mvn test
