package com.bjsxt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,ServletContextListener{
/*-------------------监听session对象-------------------------------------*/
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc=se.getSession().getServletContext();//获取application对象
		int count=(int) sc.getAttribute("count");
		sc.setAttribute("count", ++count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc=se.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count",--count);
		
	}
/*----------------------监听application对象-----------------------------*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();//获取application
		sc.setAttribute("count", 0);//在application对象存储变量来统计在线人数
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
