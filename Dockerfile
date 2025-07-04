# 1) Build de l’application avec Maven
FROM maven:3.9.10-eclipse-temurin-21 AS build
WORKDIR /app

# Copie des fichiers de config et téléchargement des dépendances
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copie du code source et compilation
COPY src src
RUN ./mvnw package -DskipTests -B

# 2) Image finale légère
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Exposez le port Spring Boot
EXPOSE 8080

# Récupère le jar construit
COPY --from=build /app/target/*.jar app.jar

# Lancement
ENTRYPOINT ["java","-jar","/app/app.jar"]
