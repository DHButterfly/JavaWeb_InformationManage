package com.bjsxt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,ServletContextListener{
/*-------------------����session����-------------------------------------*/
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc=se.getSession().getServletContext();//��ȡapplication����
		int count=(int) sc.getAttribute("count");
		sc.setAttribute("count", ++count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc=se.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count",--count);
		
	}
/*----------------------����application����-----------------------------*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();//��ȡapplication
		sc.setAttribute("count", 0);//��application����洢������ͳ����������
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
