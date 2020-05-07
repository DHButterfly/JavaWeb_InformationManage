package com.bjsxt.servlet;

import java.util.List;
import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;
/*
 * Servlet重定向路径总结：
 *  相对路径：从当前请求的路径查找资源的路径，相对路径中如果servlet包含目录，会造成重定向资源查找失败
 *  绝对路径：/虚拟项目名/资源路径，第一个/为服务器根目录
 *  请求转发中/表示项目根目录
 */
public class UserServlet extends HttpServlet {
	UserService us=new UserServiceImpl();
	Logger logger=Logger.getLogger(UserServlet.class);//日志记录器
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//设置请求编码格式
		resp.setContentType("text/html;charset=utf-8");//设置响应编码格式
		String oper=req.getParameter("oper");
		if("login".equals(oper)) {
			checkUserLogin(req,resp);//验证登录
		}else if("out".equals(oper)) {//用户退出
			userOut(req,resp);
		}else if("pwd".equals(oper)) {//修改密码
			userChangePwd(req,resp);
		}else if("show".equals(oper)){
			userShow(req,resp);
		}
		else if("reg".equals(oper)) {
			userReg(req,resp);
		}
		else {
			logger.debug("没有找到对应的操作符"+oper);
		}
	}
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String sex=req.getParameter("sex");
		int age=req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth=req.getParameter("birth");
		String[] bs=null;
		if(birth!="") {
			bs=birth.split("/");
			birth=(bs[2]+"-"+bs[0]+"-"+bs[1]);
		}
		User u=new User(0,uname,pwd,sex,age,birth);
		int index=us.userRegService(u);
		if(index>0) {
			HttpSession hs=req.getSession();
			hs.setAttribute("flag",2);
			resp.sendRedirect("/InformationManage/login.jsp");
			return;
		}
	}
	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> lu=us.userShowService();
		if(lu!=null) {
			req.setAttribute("lu",lu);
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return;
		}
	}
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String newPwd=req.getParameter("newPwd");
		User u=(User)req.getSession().getAttribute("user");
		int uid=u.getUid();
		int index=us.userChangePwdService(newPwd,uid);
		if(index>0) {
			HttpSession hs=req.getSession();
			hs.setAttribute("flag",1);
			resp.sendRedirect("/InformationManage/login.jsp");
		}else {
			
		}
	}
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs=req.getSession();
		hs.invalidate();
		resp.sendRedirect("/InformationManage/login.jsp");
	}
	//处理登录请求
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		logger.debug("uname"+uname+"  pwd  "+pwd);
		User user=us.checkUserLoginService(uname, pwd);
		if(user!=null) {
			HttpSession hs=req.getSession();
			hs.setAttribute("user", user);
			resp.sendRedirect("/InformationManage/main/main.jsp");
			return;
			//第一个/为服务器所在目录
		}else {
			req.setAttribute("flag", 0);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
	}
	
}
