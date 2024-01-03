package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.IBlogsDAO;
import com.laptrinhjavaweb.mapper.BlogWithCategoryMapper;
import com.laptrinhjavaweb.mapper.BlogsMapper;
import com.laptrinhjavaweb.mapper.CategoriesMapper;
import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.utils.PagingUtils;

import java.util.List;

public class BlogsDAO extends AbstractDAO<BlogsModel> implements IBlogsDAO {
    @Override
    public Long insert(BlogsModel blogsModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO blogs (title, slugblog, datesubmitted,");
        sql.append(" categoryid, content, createddate, createdby)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), blogsModel.getTitle(), blogsModel.getSlugBlog(), blogsModel.getDateSubmitted(),
                blogsModel.getCategoryId(), blogsModel.getContent(), blogsModel.getCreatedDate(), blogsModel.getCreatedBy());
    }

    @Override
    public BlogsModel findOne(Long id) {
        String sql = "SELECT * FROM blogs WHERE id = ?";
        List<BlogsModel> categoriesModels = query(sql, new BlogsMapper(), id);
        return categoriesModels.isEmpty() ? null : categoriesModels.get(0);
    }

    @Override
    public List<BlogsModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs");

        return query(sql.toString(), new BlogsMapper());
    }

    @Override
    public BlogsModel findOneBySlug(String slugBlog) {

        String sql = "SELECT * FROM blogs WHERE slugblog = ?";
        List<BlogsModel> blogsModelList = query(sql, new BlogsMapper(), slugBlog);
        return blogsModelList.isEmpty() ? null : blogsModelList.get(0);
    }

    @Override
    public List<BlogsModel> findByCategoryId(Long categoryId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE categoryid = ?");

        return query(sql.toString(), new BlogsMapper(), categoryId);
    }

    @Override
    public List<BlogsModel> findByKey(String key) {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE title LIKE '%"+ key +
                                                "%' OR content LIKE '%" + key + "%'");
        return search(sql.toString(), new BlogsMapper());
    }

    @Override
    public List<BlogsModel> findAllWithPageable(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs");
        sql = PagingUtils.pagingQuery(pageable, sql);
        return query(sql.toString(), new BlogsMapper());
    }

    @Override
    public List<BlogsModel> findByCategoryIdWithPageable(Long categoryId, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE categoryid = ?");
        sql = PagingUtils.pagingQuery(pageable, sql);

        return query(sql.toString(), new BlogsMapper(), categoryId);
    }



    @Override
    public List<BlogsModel> findByKeyWithPageable(String key, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE title LIKE '%"+ key +
                "%' OR content LIKE '%" + key + "%'");
        sql = PagingUtils.pagingQuery(pageable, sql);
        return search(sql.toString(), new BlogsMapper());
    }

    @Override
    public List<BlogsModel> getRecent() {
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs");

        sql.append(" ORDER BY createddate DESC");
        sql.append(" LIMIT 0,5");

        return query(sql.toString(), new BlogsMapper());
    }

    @Override
    public List<BlogWithCategoryModel> findAllWithPageablePlus(Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from blogs b inner join categories c on b.categoryid = c.id");
        sql = PagingUtils.pagingQuery(pageable, sql);
        return query(sql.toString(), new BlogWithCategoryMapper());
    }

    @Override
    public List<BlogWithCategoryModel> findByCategoryIdWithPageablePlus(Long categoryId, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from blogs b inner join categories c on b.categoryid = c.id WHERE b.categoryid = ?");
        sql = PagingUtils.pagingQuery(pageable, sql);

        return query(sql.toString(), new BlogWithCategoryMapper(), categoryId);
    }
}
