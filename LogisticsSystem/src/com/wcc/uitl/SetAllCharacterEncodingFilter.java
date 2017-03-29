package com.wcc.uitl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
//解决GET和POST请求参数和响应输出的编码过滤器
public class SetAllCharacterEncodingFilter implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;
		try{
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)resp;
		}catch(Exception e){
			throw new RuntimeException("non-http request");
		}
		String encoding = "UTF-8";//默认参数
		String value = filterConfig.getInitParameter("encoding");
		if(value!=null){
			encoding = value;
		}
		
		request.setCharacterEncoding(encoding);//POST请求方式
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		MyHttpServletRequest mrequest = new MyHttpServletRequest(request);
		chain.doFilter(mrequest, response);
	}

	public void destroy() {

	}

}
class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public MyHttpServletRequest(HttpServletRequest request){
		super(request);
		this.request = request;
	}
	public String getParameter(String name) {
		String value = request.getParameter(name);
		if(value==null)
			return value;
		try {
			if("get".equalsIgnoreCase(request.getMethod()))
				value = new String(value.getBytes("ISO-8859-1"),request.getCharacterEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
