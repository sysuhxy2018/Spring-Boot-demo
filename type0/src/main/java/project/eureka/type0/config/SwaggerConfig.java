package project.eureka.type0.config;

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

@Configuration
@EnableSwagger2	//启用swagger
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInf())
            .select()
            .apis(RequestHandlerSelectors.basePackage("project.eureka.type0.db1"))	//定义要使用swagger的controller所在路径（包名）
            .paths(PathSelectors.any())
            .build();
    }
    
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
            .title("系统RESTful API文档")
            .contact(new Contact("eureka", "https://www.abc.xyz", "123@456.com"))
            .version("1.0")
            .build();
    }
}