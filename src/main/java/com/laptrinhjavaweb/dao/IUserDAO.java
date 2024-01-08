package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.dao.impl.UserDAO;
import com.laptrinhjavaweb.model.UserModel;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserDAO> {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
	List<UserModel> findByStatus(Integer status);
}
