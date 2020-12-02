package dengtao.Model.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="UserloginFilter",urlPatterns="/jsp/*")
public class SysFilter implements Filter{
	/**
	 * 登陆过滤器，未登录不能访问jsp目录下的jsp页面
	 * */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		if(request.getSession().getAttribute("USER_SESSION")==null) {
			response.sendRedirect(request.getContextPath() + "/index.html");
		}else {
			chain.doFilter(request, response);
		}
	}
}
