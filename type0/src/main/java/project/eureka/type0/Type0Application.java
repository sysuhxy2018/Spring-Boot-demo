package project.eureka.type0;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@MapperScan("project.eureka.type0.dao")	//此注解表示动态扫描DAO接口所在包，实际上不加这条语句也可以找到
@ServletComponentScan
public class Type0Application {

	public static void main(String[] args) {
		SpringApplication.run(Type0Application.class, args);
	}

}
