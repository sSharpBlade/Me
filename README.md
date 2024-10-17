<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>edwin.lopez</groupId>
    <artifactId>AlgoritmoCesar</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.10</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.6.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.6.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.6.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <exec.mainClass>edwin.lopez.algoritmocesar.AlgoritmoCesar</exec.mainClass>
    </properties>
</project>
============================0
version: '3.9'
services:
  postgresql:
    image: postgres:alpine3.14 # Posgresql 14.1
    restart: always
    environment:
      POSTGRES_USER: sonarqube
      POSTGRES_PASSWORD: sonarqube.2022*
      PGDATA: /var/lib/postgresql/data/pgdata
    expose:
      - 5432
    volumes:
      - ./postgresql_data/postgresql:/var/lib/postgresql/data
    networks:
      sonarqube_community_network:
        aliases:
          - postgresql_net
  sonarqube:
    image: sonarqube:9.9.6-community
    restart: always
    depends_on:
      - postgresql
    ports:
      - 9000:9000
    environment:
      SONAR_JDBC_URL: jdbc:postgresql://postgresql_net/sonarqube
      SONAR_JDBC_USERNAME: sonarqube
      SONAR_JDBC_PASSWORD: 'sonarqube.2022*'
      SONAR_JDBC_MAXACTIVE: 60
      SONAR_JDBC_MAXIDLE: 5
      SONAR_JDBC_MINIDLE: 2
      SONAR_JDBC_MAXWAIT: 5000
    volumes:
      - ./sonarqube_data/data:/opt/sonarqube/data
      - ./sonarqube_logs/logs:/opt/sonarqube/logs
      - ./sonarqube_extensions/extensions:/opt/sonarqube/extensions
      - /etc/localtime:/etc/localtime
      - /etc/timezone:/etc/timezone
    networks:
      sonarqube_community_network:
        aliases:
          - sonarqube_net
volumes:
  sonarqube_data: {}
  sonarqube_logs: {}
  sonarqube_extensions: {}
  postgresql_data: {}
networks:
  sonarqube_community_network:
    driver: bridge
    ipam:
      driver: default
