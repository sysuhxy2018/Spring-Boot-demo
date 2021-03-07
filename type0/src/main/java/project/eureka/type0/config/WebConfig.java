package project.eureka.type0.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import project.eureka.type0.bean.TimeInterceptor;

@SuppressWarnings("deprecation")
@Configuration	//Java方式配置文件专用注解
public class WebConfig extends WebMvcConfigurerAdapter{
	@Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);	//注册我们自定义的拦截器
    }
}
