package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;

public class NewsDAO implements INewsDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newsservlet";
			String user = "root";
			String password = "20520194@Gm";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		List<NewsModel> results = new ArrayList<>();
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		// open connection
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				// init parameter
				statement.setLong(1, categoryId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewsModel newsModel = new NewsModel();
					newsModel.setId(resultSet.getLong("id"));
					newsModel.setTitle(resultSet.getString("title"));
					results.add(newsModel);
				}

				return results;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}

		}
		return null;
	}

}
