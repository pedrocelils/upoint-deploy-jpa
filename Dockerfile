# Fase de build - usa imagem oficial com Maven e OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copia o código do seu repositório para o container
COPY . .

# Rodar o Maven para compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Fase final - usa imagem mais leve do OpenJDK para rodar a aplicação
FROM eclipse-temurin:17-jdk-alpine

# Definir diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na fase de build
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta 8080
EXPOSE 8080

# Comando para rodar o app quando o container for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]
