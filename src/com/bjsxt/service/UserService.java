package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.User;

public interface UserService {
	User checkUserLoginService(String uname,String pwd);
	int userChangePwdService(String newPwd, int uid);
	List<User> userShowService();//获取所有用户信息
	int userRegService(User user);//用户注册
}
