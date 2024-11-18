# Gerenciamento de Fazendas - Fase C

## Funcionalidades
- **Migração de Código:** O código da Fase B foi migrado para o novo projeto, mantendo todas as funcionalidades anteriores.
- **Criação de Pessoas (POST /persons):** Implementada a rota para cadastrar novas pessoas no sistema, com os campos `username`, `password`, e `roles`. A resposta retorna os campos `id`, `username`, e `role` (sem expor o `password`).
- **Autenticação e JWT:**
  - **POST /auth/login:** Rota implementada para autenticar usuários e retornar um token JWT.
  - Configuração do Spring Security para proteção de rotas com base em roles.
- **Controle de Acesso a Rotas:**
  - **GET /farms:** Acessível apenas para usuários autenticados com as roles `USER`, `MANAGER` ou `ADMIN`.
  - **GET /crops:** Acessível apenas para usuários autenticados com as roles `MANAGER` ou `ADMIN`.
  - **GET /fertilizers:** Acessível apenas para usuários autenticados com a role `ADMIN`.

## Objetivo
O projeto Gerenciamento de Fazendas - Fase C foi desenvolvido para adicionar recursos de autenticação, controle de acesso baseado em roles e melhoria na segurança das rotas. O objetivo é consolidar conhecimentos em segurança de APIs RESTful com Spring Boot, JWT e controle granular de acessos.

## Stacks Utilizadas
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Endpoints da API
1. **POST /persons:** Cria uma nova pessoa com autenticação básica.
2. **POST /auth/login:** Retorna um token JWT após autenticação bem-sucedida.
3. **GET /farms:** Lista todas as fazendas cadastradas (restrito a `USER`, `MANAGER` ou `ADMIN`).
4. **GET /crops:** Lista todas as plantações cadastradas (restrito a `MANAGER` ou `ADMIN`).
5. **GET /fertilizers:** Lista todos os fertilizantes cadastrados (restrito a `ADMIN`).

## Como Utilizar
1. Clone este repositório para o seu ambiente local.
2. Certifique-se de ter o Java e o Maven instalados em seu sistema.
3. Navegue até o diretório raiz do projeto.
4. Execute o comando `mvn clean install` para compilar o projeto e instalar as dependências.
5. Após a instalação das dependências, execute `mvn spring-boot:run` para iniciar a aplicação.
6. Utilize as rotas da API conforme descrito acima, lembrando-se de autenticar antes de acessar rotas restritas.

## Dockerização
1. Certifique-se de ter o Docker instalado no sistema.
2. Navegue até o diretório raiz do projeto.
3. Execute `docker build -t farm-management-app .` para construir a imagem Docker.
4. Inicie o contêiner com `docker run -p 8080:8080 farm-management-app`.

## Aprendizados e Desenvolvimento
Nesta fase do projeto, foram consolidadas habilidades em:

- **Autenticação:** Implementação de autenticação com JWT para proteger rotas da API.
- **Spring Security:** Configuração de segurança para rotas com base em roles.
- **Segurança de Dados:** Garantia de que apenas usuários com permissões apropriadas possam acessar rotas restritas.
- **Migração de Código:** Reutilização e integração do código da fase anterior no novo projeto.

## Agradecimentos
Obrigado por conferir o projeto Gerenciamento de Fazendas - Fase C. Este projeto foi desenvolvido para aprofundar conhecimentos em segurança de APIs RESTful com Spring Boot. Feedbacks e sugestões são bem-vindos!

**Autor:** [Rodrigo Cesar Christofani Junior](https://github.com/Christofani)
