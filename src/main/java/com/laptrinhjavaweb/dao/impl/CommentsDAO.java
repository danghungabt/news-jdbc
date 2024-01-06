package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.mapper.CommentsMapper;
import com.laptrinhjavaweb.mapper.CountCommentMapper;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.CountCommentModel;

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

    @Override
    public Integer getTotalItemByBlogId(Long id) {
        String sql = "SELECT count(*) FROM comments where blogid = " + id;
        return count(sql);
    }

    @Override
    public List<CountCommentModel> getCountComment() {
        String sql = "SELECT blogid, count(*) as count FROM comments group by blogid";
        return query(sql, new CountCommentMapper());
    }
}
