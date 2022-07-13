# Wishlist API
Guia para executar a aplicação com Docker e Maven.

Requisitos:

- JDK11+
- Docker
- Postman

## 1 - Instalando na sua máquina
```
git clone https://github.com/LucasTengan/teste-backend-luizalabs.git
cd teste-backend-luizalabs
```
Obs: caso queira pode baixar o ZIP


## 2 - Preparando ambiente baixando MongoDB via docker
```
docker run -d --name mongodb-docker -v ./db/:/data/db -p 27017:27017 mongo
```
Nesta etapa, é preciso verificar se não há nenhum servidor MongoDB rodando localmente, caso já possua e esteja mapeado para a porta 27017, não é necessário rodar o comando no Docker
## 3 - Executando aplicação via bash na pasta raiz (/teste-backend-luizalabs)
```
.\mvnw spring-boot:run
```
## 3.1 - Executando aplicação via cmd na pasta raiz (/teste-backend-luizalabs)
```
mvn spring-boot:run
```

A API estará disponível através da URL `localhost:8080`

Assim que executar a aplicação, poderá consultar sua documentação através da URL `localhost:8080/swagger-ui.html`

## 4 - Testando a aplicação
```
caso tenha o postman instalado, basta importar a collection presente neste repositório e testar os endpoints
se não tiver, pode acessar a URl de documentação, também é possível testar por lá
```
Obrigado pela atenção :)

Obs: também tentei conteinerizar a aplicação através do Dockerfile e docker-compose.yml, para evitar a necessidade de ter o JDK11 instalado. Porém, obtive alguns problemas de conexão da aplicação com o MongoDB e não encontrei soluções, sendo assim, optei pelo caminho acima de apenas conteinerizar o mongo.
