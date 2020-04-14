package com.bjsxt.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import com.bjsxt.dao.UserDao;
import com.bjsxt.dao.impl.UserDaoImpl;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;

public class UserServiceImpl implements UserService{
	Logger logger=Logger.getLogger(UserServiceImpl.class);//������־����
	UserDao ud=new UserDaoImpl();//����dao�����
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		User u=ud.checkUserLoginDao(uname, pwd);
		if(u!=null) {
			logger.debug(uname+"��½�ɹ���");
		}else {
			logger.debug(uname+"��½ʧ�ܣ�");
		}
		return u;
	}
	@Override
	public int userChangePwdService(String newPwd, int uid) {
		logger.debug(uid+"�����������޸�����");
		int index=ud.userChangePwdDao(newPwd, uid);
		if(index>0) {
			logger.debug(uid+":�����޸ĳɹ���");
		}else {
			logger.debug(uid+":�����޸�ʧ��");
		}
		return index;
	}
	@Override
	public List<User> userShowService() {
		List<User> lu=ud.userShowDao();
		logger.debug("��ʾ�����û���Ϣ��"+lu);
		return lu;
	}
	@Override
	public int userRegService(User user) {
		
		return ud.userRegDao(user);
	}
		
}
