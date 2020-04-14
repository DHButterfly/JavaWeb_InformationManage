package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.pojo.User;

//根据用户名和密码查询用户信息
public interface UserDao {
	User checkUserLoginDao(String uname,String pwd);
	int userChangePwdDao(String newPwd, int uid);
	List<User> userShowDao();
	int userRegDao(User user);
}
