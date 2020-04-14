package com.bjsxt.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import com.bjsxt.dao.UserDao;
import com.bjsxt.dao.impl.UserDaoImpl;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;

public class UserServiceImpl implements UserService{
	Logger logger=Logger.getLogger(UserServiceImpl.class);//申明日志对象
	UserDao ud=new UserDaoImpl();//申明dao层对象
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		User u=ud.checkUserLoginDao(uname, pwd);
		if(u!=null) {
			logger.debug(uname+"登陆成功！");
		}else {
			logger.debug(uname+"登陆失败！");
		}
		return u;
	}
	@Override
	public int userChangePwdService(String newPwd, int uid) {
		logger.debug(uid+"：发起密码修改请求！");
		int index=ud.userChangePwdDao(newPwd, uid);
		if(index>0) {
			logger.debug(uid+":密码修改成功！");
		}else {
			logger.debug(uid+":密码修改失败");
		}
		return index;
	}
	@Override
	public List<User> userShowService() {
		List<User> lu=ud.userShowDao();
		logger.debug("显示所有用户信息："+lu);
		return lu;
	}
	@Override
	public int userRegService(User user) {
		
		return ud.userRegDao(user);
	}
		
}
