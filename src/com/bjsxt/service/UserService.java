package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.User;

public interface UserService {
	User checkUserLoginService(String uname,String pwd);
	int userChangePwdService(String newPwd, int uid);
	List<User> userShowService();//��ȡ�����û���Ϣ
	int userRegService(User user);//�û�ע��
}
