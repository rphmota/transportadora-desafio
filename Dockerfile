# Usar a imagem base do JDK 17 para a construção
FROM openjdk:17-slim as build

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de configuração do Maven e o código fonte
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Compilar o aplicativo usando Maven, sem executar testes
RUN ./mvnw package -DskipTests

# Usar a imagem base do JRE 17 para a execução
FROM openjdk:17-slim
WORKDIR /app

# Copiar o JAR compilado do estágio de construção para o estágio de execução
COPY --from=build /app/target/*.jar app.jar

# Definir a porta que será exposta pelo container
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
