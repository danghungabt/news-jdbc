package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.mapper.CommentsMapper;
import com.laptrinhjavaweb.model.CommentsModel;

import java.util.List;

public class CommentsDAO extends AbstractDAO<CommentsModel> implements ICommentsDAO {
    @Override
    public Long insert(CommentsModel commentsModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO comments (name, email, website, content,");
        sql.append(" parentid, commentdate, blogid, createddate, createdby)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        if(commentsModel.getParentId() == null){
            commentsModel.setParentId(0L);
        }
        return insert(sql.toString(), commentsModel.getName(), commentsModel.getEmail(), commentsModel.getWebsite(),
                commentsModel.getContent(), commentsModel.getParentId(), commentsModel.getCommentDate(),
                commentsModel.getBlogId(), commentsModel.getCreatedDate(), commentsModel.getCreatedBy());
    }

    @Override
    public CommentsModel findOne(Long id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        List<CommentsModel> commentsModels = query(sql, new CommentsMapper(), id);
        return commentsModels.isEmpty() ? null : commentsModels.get(0);
    }

    @Override
    public List<CommentsModel> findAll() {
        return null;
    }

    @Override
    public List<CommentsModel> findByBlogId(Long blogId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM comments WHERE blogid = ?");
        return query(sql.toString(), new CommentsMapper(), blogId);
    }

    @Override
    public List<CommentsModel> getRecent() {
        StringBuilder sql = new StringBuilder("SELECT * FROM comments");

        sql.append(" ORDER BY createddate DESC");
        sql.append(" LIMIT 0,5");

        return query(sql.toString(), new CommentsMapper());
    }
}
