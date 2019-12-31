package ${project.basePackage}.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .groupName("${project.description}")
        .select()
        .apis(RequestHandlerSelectors.basePackage("${project.basePackage}"))
        .paths(PathSelectors.any())
        .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        .title("${project.description}API文档")
        .description("API描述：${project.description}")
        .contact(new Contact("xx事业部", "http://localhost:${project.serverPort}${project.serverPath}/swagger-ui.html", "xxx@xx.com"))
        .version("1.0.0")
        .build();
    }
}
