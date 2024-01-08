package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserDAO> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append("\nINNER JOIN role AS r ON r.id = u.roleid");
		sql.append("\nWHERE username = ? AND password = ? AND status = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), userName, password, status);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public List<UserModel> findByStatus(Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u WHERE status = ?");
		return query(sql.toString(), new UserMapper(), status);
	}

}
