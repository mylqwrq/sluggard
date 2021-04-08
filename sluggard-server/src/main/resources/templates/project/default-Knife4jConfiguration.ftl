package ${project.basePackage}.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("${project.description}接口文档")
                        .description("# API描述：${project.description}")
                        .termsOfServiceUrl("http://localhost:${project.serverPort}${project.serverPath}/doc.html")
                        .version("1.0.0")
                        .build())
                .groupName("${project.description}")
                .select()
                .apis(RequestHandlerSelectors.basePackage("${project.basePackage}"))
                .paths(PathSelectors.any())
                .build();
    }
}
