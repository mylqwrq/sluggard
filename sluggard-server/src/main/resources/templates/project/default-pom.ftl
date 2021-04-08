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
        <maven-resources-plugin.version>3.1.0</maven-resources-plugin.version>
        <versions-maven-plugin.version>2.7</versions-maven-plugin.version>

        <spring-boot.version>2.2.1.RELEASE</spring-boot.version>
        <apache-commons-compress.vesion>1.19</apache-commons-compress.vesion>
        <druid.version>1.1.21</druid.version>
        <mybatis-boot.version>2.1.1</mybatis-boot.version>
        <pagehelper-boot.version>1.2.13</pagehelper-boot.version>
        <cglib.version>3.3.0</cglib.version>
        <jjwt.version>0.9.1</jjwt.version>
        <fastjson.version>1.2.62</fastjson.version>
        <jackson.version>2.10.1</jackson.version>
        <knife4j.version>2.0.8</knife4j.version>
        <guava.version>28.1-jre</guava.version>
        <pinyin4j.version>2.5.1</pinyin4j.version>
        <easyexcel.version>2.1.6</easyexcel.version>
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

        <!-- Apache -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-compress</artifactId>
            <version>${r'$'}{apache-commons-compress.vesion}</version>
        </dependency>

        <!-- Druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${r'$'}{druid.version}</version>
        </dependency>

        <!-- MyBatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${r'$'}{mybatis-boot.version}</version>
        </dependency>

        <!-- PageHelper -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>${r'$'}{pagehelper-boot.version}</version>
        </dependency>

        <!-- Cglib -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib-nodep</artifactId>
            <version>${r'$'}{cglib.version}</version>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>${r'$'}{jjwt.version}</version>
        </dependency>

        <!-- FastJson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${r'$'}{fastjson.version}</version>
        </dependency>

        <!-- Jackson -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${r'$'}{jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-yaml</artifactId>
            <version>${r'$'}{jackson.version}</version>
        </dependency>

        <!-- knife4j http://{ip}:{port}/{project}/doc.html -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>${r'$'}{knife4j.version}</version>
        </dependency>

        <!-- Guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>${r'$'}{guava.version}</version>
        </dependency>

        <!-- Pinyin4j -->
        <dependency>
            <groupId>com.belerweb</groupId>
            <artifactId>pinyin4j</artifactId>
            <version>${r'$'}{pinyin4j.version}</version>
        </dependency>

        <!-- EasyExcel -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>${r'$'}{easyexcel.version}</version>
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
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>${r'$'}{maven-resources-plugin.version}</version>
                <configuration>
                    <nonFilteredFileExtensions>
                        <nonFilteredFileExtension>woff</nonFilteredFileExtension>
                        <nonFilteredFileExtension>woff2</nonFilteredFileExtension>
                        <nonFilteredFileExtension>eot</nonFilteredFileExtension>
                        <nonFilteredFileExtension>ttf</nonFilteredFileExtension>
                        <nonFilteredFileExtension>svg</nonFilteredFileExtension>
                    </nonFilteredFileExtensions>
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