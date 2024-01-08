package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

import java.util.Map;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
	Map<String, String> findByStatus(Integer status);
}
