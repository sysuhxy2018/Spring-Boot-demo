package project.eureka.type0;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@ConfigurationProperties(prefix="test")	//批量导入配置，prefix + 变量名要和配置文件中的名字完全匹配。这样就不用每个变量都设置@Value注解
@PropertySource("classpath:test.properties")	//自定义的配置文件路径
public class Properties {
	
//	@Value("${user.system.info}")
//	private String system;
//
//	public String getSystem() {
//		return system;
//	}
//
//	public void setSystem(String system) {
//		this.system = system;
//	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
