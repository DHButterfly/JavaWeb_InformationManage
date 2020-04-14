package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bjsxt.dao.UserDao;
import com.bjsxt.pojo.User;
import java.sql.PreparedStatement;

public class UserDaoImpl implements UserDao{
//根据用户名和密码查询用户信息
	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tonghua?useSSL=false&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","5120154230");
			Logger logger=Logger.getLogger(UserDaoImpl.class);
			logger.debug("数据库连接成功！！！");
			String sql="select * from t_user where uname=? and pwd=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2,pwd);
			rs=ps.executeQuery();
			while(rs.next()) {
				user=new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setPwd(rs.getString("pwd"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setBirth(rs.getString("birth"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public int userChangePwdDao(String newPwd, int uid) {
		Connection conn=null;
		PreparedStatement ps=null;
		int index=-1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tonghua?useSSL=false&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","5120154230");
			String sql="update t_user set pwd=? where uid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			index=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return index;//成功修改则返回1
	}

	@Override
	public List<User> userShowDao() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User> lu=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tonghua?useSSL=false&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","5120154230");
			String sql="select * from t_user";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			lu=new ArrayList<User>();
			while(rs.next()) {
				User user=new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setPwd(rs.getString("pwd"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setBirth(rs.getString("birth"));
				lu.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lu;
	}

	@Override
	public int userRegDao(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		int index=-1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tonghua?useSSL=false&suseUnicode=true&amp;characterEncoding=utf-8";
			conn=DriverManager.getConnection(url,"root","5120154230");
			String sql="insert into t_user values(default,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUname());
			ps.setString(2,user.getPwd());
			ps.setString(3,user.getSex());
			ps.setInt(4,user.getAge());
			ps.setString(5,user.getBirth());
			index=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return index;//成功修改则返回1
	}
	
}
