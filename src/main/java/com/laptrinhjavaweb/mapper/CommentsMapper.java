package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CommentsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsMapper implements RowMapper<CommentsModel> {

	@Override
	public CommentsModel mapRow(ResultSet resultSet) {
		try {
			CommentsModel commentsModel = new CommentsModel();
			commentsModel.setId(resultSet.getLong("id"));
			commentsModel.setName(resultSet.getString("name"));
			commentsModel.setEmail(resultSet.getString("email"));
			commentsModel.setBlogId(resultSet.getLong("blogid"));
			commentsModel.setWebsite(resultSet.getString("website"));
			commentsModel.setContent(resultSet.getString("content"));
			commentsModel.setParentId(resultSet.getLong("parentid"));
			commentsModel.setCommentDate(resultSet.getTimestamp("commentdate"));
			return commentsModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
