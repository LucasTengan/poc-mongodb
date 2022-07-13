# Wishlist API
Guia para executar a aplicação com Docker e Maven.

Requisitos:

- JDK11+
- Docker

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

A API estará disponível através da URL `localhost:8080`

Assim que executar a aplicação, poderá consultar sua documentação através da URL `localhost:8080/swagger-ui.html`


Obs: também tentei conteinerizar a aplicação através do Dockerfile e docker-compose.yml, porém obtive alguns problemas de conexão da aplicação com o MongoDB que não encontrei soluções, então optei pelo caminho acima.
