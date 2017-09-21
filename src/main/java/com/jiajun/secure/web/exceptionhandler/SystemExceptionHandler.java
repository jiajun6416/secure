package com.jiajun.secure.web.exceptionhandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class SystemExceptionHandler  extends SimpleMappingExceptionResolver{
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String message = ex.getMessage();
		response.setContentType("application/json; charset=utf-8");
		 PrintWriter write = null;
			try {
				write = response.getWriter();
				write.write(message);
				write.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(write != null) {
					write.close();
				}
				write = null;
			}
		return null;
	}
	
}
