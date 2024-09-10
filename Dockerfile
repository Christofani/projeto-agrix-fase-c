# Primeiro estágio: Construção do pacote
FROM maven:3-openjdk-17 AS build-image

# Define o diretório de trabalho
WORKDIR /to-build-app

# Copia os arquivos do projeto para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Baixa as dependências do Maven para cache
RUN mvn dependency:go-offline

# Constrói o pacote JAR
RUN mvn package -DskipTests

# Segundo estágio: Construção da imagem final
FROM eclipse-temurin:17-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR construído do primeiro estágio
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Exponha a porta que a aplicação usa
EXPOSE 8080

# Define o ponto de entrada da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
