package wanczy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PermissionFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Within SimpleFilter2:Filtering the Request...");
        chain.doFilter(request, response);// 把处理发送到下一个过滤器
        System.out .println("Within SimpleFilter2:Filtering the Response...");
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
