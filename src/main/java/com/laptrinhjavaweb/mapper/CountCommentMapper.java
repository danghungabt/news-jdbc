package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.CountCommentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountCommentMapper implements RowMapper<CountCommentModel> {
    @Override
    public CountCommentModel mapRow(ResultSet rs) {
        try {
            CountCommentModel countCommentModel = new CountCommentModel();
            countCommentModel.setBlogId(rs.getLong("blogid"));
            countCommentModel.setCount(rs.getInt("count"));
            return  countCommentModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
