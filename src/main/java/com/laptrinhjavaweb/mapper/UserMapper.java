package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet rs) {
        try {
            UserModel userModel = new UserModel();
            userModel.setId(rs.getLong("id"));
            userModel.setUserName(rs.getString("username"));
            userModel.setFullName(rs.getString("fullname"));
            userModel.setPassword(rs.getString("password"));
            userModel.setStatus(rs.getInt("status"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(rs.getString("code"));
                role.setName(rs.getString("name"));
                userModel.setRole(role);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
