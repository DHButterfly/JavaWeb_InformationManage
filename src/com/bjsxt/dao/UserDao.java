package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.pojo.User;

//�����û����������ѯ�û���Ϣ
public interface UserDao {
	User checkUserLoginDao(String uname,String pwd);
	int userChangePwdDao(String newPwd, int uid);
	List<User> userShowDao();
	int userRegDao(User user);
}
