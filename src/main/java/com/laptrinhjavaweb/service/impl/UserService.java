package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO; 
	
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

	@Override
	public Map<String, String> findByStatus(Integer status) {
		Map<String, String> result = new LinkedHashMap<>();
		List<UserModel> users = userDAO.findByStatus(1);
		for(UserModel item: users){
			result.put(item.getUserName(), item.getFullName());
		}
		return result;
	}

}
