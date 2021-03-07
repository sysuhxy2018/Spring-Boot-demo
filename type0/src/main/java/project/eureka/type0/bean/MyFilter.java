package project.eureka.type0.bean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 关于url的路径。这里用的是相对路径，相对项目（context-path）的路径。而context-path又是相对于host的路径，如 /xxx，默认为空。
 * 也就是除去host和项目名部分剩下的路径。包括urlPatterns，getServletPath和getRequestDispatcher都是这种相对路径。
 */
@Component
@Order(1)	//如果出现多个filter，则该filter排第一层
@WebFilter(filterName = "pagerFilter", urlPatterns = "/*")	//只有满足匹配规则的url才会进入该filter，否则将直接绕过它
public class MyFilter implements Filter{

	@Override
    public void init(FilterConfig filterConfig) {
        //初始化，可以记录一些日志信息
    }

	//这部分其实还是旧的Java servlet方面的知识，和Spring关系不大
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String path = httpServletRequest.getServletPath();
        //做一个简单的路径修改，使得不准确的路径变成准确的
        if (path.indexOf("/pager") >= 0 && path.indexOf("/player/pager") != 0) {
        	//这是服务器在应用内部的转发，自始至终都是同一次请求和响应，地址栏url不变。
        	path = "/player" + path.substring(path.indexOf("/pager"));
        	httpServletRequest.getRequestDispatcher(path).forward(httpServletRequest, httpServletResponse);
        	
        	//这是重定向，相当于做了两次请求和响应（302 + 200），地址栏url会改变。
        	/*
        	path = "http://localhost:8080" + path;
        	httpServletResponse.sendRedirect(path);
        	*/
        }
        else {
        	filterChain.doFilter(servletRequest, servletResponse);	//相当于直接穿过filter，不做任何处理
        } 
    }

    @Override
    public void destroy() {
    	//销毁，回收一些资源
    }
}
