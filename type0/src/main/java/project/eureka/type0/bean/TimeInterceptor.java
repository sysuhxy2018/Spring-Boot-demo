package project.eureka.type0.bean;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component	//Bean专用注解，如果想要autowired必须加上
public class TimeInterceptor implements HandlerInterceptor {
		//在请求的方法执行前执行
	 	@Override
	    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
	        System.out.println("处理拦截之前");
	        httpServletRequest.setAttribute("startTime", new Date().getTime());
	        System.out.println(((HandlerMethod) o).getBean().getClass().getName());	//请求所使用的Controller的完整名字（路径）
	        System.out.println(((HandlerMethod) o).getMethod().getName());	//请求所使用的Controller的方法名字
	        return true;
	    }

	 	//请求的方法执行成功后才会执行
	    @Override
	    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
	        System.out.println("开始处理拦截");
	        Long start = (Long) httpServletRequest.getAttribute("startTime");
	        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
	    }

	    //请求调用完成后回调方法，即在视图渲染完成后回调
	    //视图在jsp/thymeleaf中比较常用。它们相当于有一个HTML的模板，然后controller把方法返回的数据填充到模板中，最后组装成完整的HTML页面响应。
	    //现在一般都是前后端分离了，也就是直接返回JSON格式的数据给前端，很少需要在后端进行视图渲染，所以很少用这个方法。
	    @Override
	    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	        System.out.println("处理拦截之后");
	        Long start = (Long) httpServletRequest.getAttribute("startTime");
	        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
	        System.out.println("异常信息 " + e);
	    }
}
