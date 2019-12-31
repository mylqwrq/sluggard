<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${project.groupId}</groupId>
    <artifactId>${project.artifactId}</artifactId>
    <version>${project.version}</version>
    <packaging>${project.packaging}</packaging>

    <name>${project.name}</name>
    <description>${project.description}</description>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven-compiler-plugin.version>3.8.1</maven-compiler-plugin.version>
        <versions-maven-plugin.version>2.7</versions-maven-plugin.version>

        <spring-boot.version>2.2.1.RELEASE</spring-boot.version>
        <jackson-dataformat.version>2.10.1</jackson-dataformat.version>
        <druid.version>1.1.21</druid.version>
        <mybatis-boot.version>2.1.1</mybatis-boot.version>
        <pagehelper-boot.version>1.2.13</pagehelper-boot.version>
        <swagger.version>2.9.2</swagger.version>
        <swagger-bootstrap-ui.version>1.9.6</swagger-bootstrap-ui.version>
        <guava.version>28.1-jre</guava.version>
        <mylq.version>1.0.1</mylq.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${r'$'}{spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- SpringBoot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- MySQL -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- 加上这个才能辨认到log4j2.yml文件 -->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-yaml</artifactId>
            <version>${r'$'}{jackson-dataformat.version}</version>
        </dependency>

        <!-- Druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${r'$'}{druid.version}</version>
        </dependency>

        <!-- MyBatis Boot -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${r'$'}{mybatis-boot.version}</version>
        </dependency>

        <!-- PageHelper Boot -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>${r'$'}{pagehelper-boot.version}</version>
        </dependency>

        <!-- Swagger http://{ip}:{port}/{project}/swagger-ui.html -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${r'$'}{swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${r'$'}{swagger.version}</version>
        </dependency>

        <!-- Swagger-UI美化版 http://{ip}:{port}/{project}/doc.html -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>swagger-bootstrap-ui</artifactId>
            <version>${r'$'}{swagger-bootstrap-ui.version}</version>
        </dependency>

        <!-- Guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>${r'$'}{guava.version}</version>
        </dependency>

        <dependency>
            <groupId>com.mylq</groupId>
            <artifactId>mylq-core</artifactId>
            <version>${r'$'}{mylq.version}</version>
        </dependency>
    </dependencies>

    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <profileActive>dev</profileActive>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <profileActive>test</profileActive>
            </properties>
        </profile>
        <profile>
            <id>uat</id>
            <properties>
                <profileActive>uat</profileActive>
            </properties>
        </profile>
        <profile>
            <id>prod</id>
            <properties>
                <profileActive>prod</profileActive>
            </properties>
        </profile>
    </profiles>

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${r'$'}{maven-compiler-plugin.version}</version>
                <configuration>
                    <source>${r'$'}{java.version}</source>
                    <target>${r'$'}{java.version}</target>
                    <encoding>${r'$'}{project.build.sourceEncoding}</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>versions-maven-plugin</artifactId>
                <version>${r'$'}{versions-maven-plugin.version}</version>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${r'$'}{spring-boot.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>