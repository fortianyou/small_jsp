package wanczy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		System.out.println("Within SimpleFilter1:Filtering the Request...");
		if(false){
			chain.doFilter(request, response);// 把处理发送到下一个过滤器
		}else{
			res.sendRedirect(req.getContextPath()+"/index.jsp");
		}
        
        System.out .println("Within SimpleFilter1:Filtering the Response...");
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
