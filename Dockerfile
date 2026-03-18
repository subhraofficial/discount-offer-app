FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 10000

CMD sh -c "java -jar $(ls target/*.jar | grep -v 'original' | head -n 1) --server.port=10000"