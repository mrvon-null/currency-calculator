FROM azul/zulu-openjdk-alpine:25
LABEL authors="von"
COPY build/libs/curcalc-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
