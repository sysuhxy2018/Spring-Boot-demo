package project.eureka.type0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({Properties.class})	//项目启用ConfigurationProperties
public class IndexController {
	
	@Autowired
	private Properties properties;
	
	@RequestMapping("/")
	String showIndex() {
		return "My name is: " + properties.getName();
	}
	
}
