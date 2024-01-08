package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.builder.CategoriesBuilder;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.mapper.CategoriesMapper;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.utils.PagingUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class CategoriesDAO extends AbstractDAO<CategoriesModel> implements ICategoriesDAO {
    @Override
    public Long insert(CategoriesModel categoriesModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO categories (category, slugcategory,");
        sql.append(" createddate, createdby)");
        sql.append(" VALUES(?, ?, ?, ?)");
        return insert(sql.toString(), categoriesModel.getCategory(), categoriesModel.getSlugCategory(),
                categoriesModel.getCreatedDate(), categoriesModel.getCreatedBy());
    }

    @Override
    public void update(CategoriesModel categoriesModel) {
        StringBuilder sql = new StringBuilder("UPDATE categories SET category = ?, slugcategory = ?,");
        sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), categoriesModel.getCategory(), categoriesModel.getSlugCategory(),
                categoriesModel.getCreatedDate(), categoriesModel.getCreatedBy(),
                categoriesModel.getModifiedDate(), categoriesModel.getModifiedBy(),
                categoriesModel.getId());
    }

    @Override
    public CategoriesModel findOne(Long id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoriesModel> categoriesModels = query(sql, new CategoriesMapper(), id);
        return categoriesModels.isEmpty() ? null : categoriesModels.get(0);
    }

    @Override
    public List<CategoriesModel> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories");
        sql = PagingUtils.pagingQuery(pageable, sql);

        return query(sql.toString(), new CategoriesMapper());
    }

    @Override
    public List<CategoriesModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories");

        return query(sql.toString(), new CategoriesMapper());
    }

    @Override
    public CategoriesModel findOneBySlug(String slugCategory) {
        String sql = "SELECT * FROM categories WHERE slugcategory = ?";
        List<CategoriesModel> categoriesModels = query(sql, new CategoriesMapper(), slugCategory);
        return categoriesModels.isEmpty() ? null : categoriesModels.get(0);
    }

    @Override
    public List<CategoriesModel> findAllWithPageable(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories");
        sql = PagingUtils.pagingQuery(pageable, sql);
        return query(sql.toString(), new CategoriesMapper());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM categories";
        return count(sql);
    }

    @Override
    public List<CategoriesModel> findByCondition(Pageable pageable, CategoriesBuilder categoriesBuilder){
        StringBuilder sql = new StringBuilder("SELECT * FROM categories WHERE 1=1");
        sql = buildQueryCommon(sql, categoriesBuilder);
        sql = PagingUtils.pagingQuery(pageable, sql);

        return query(sql.toString(), new CategoriesMapper(), categoriesBuilder.getCreatedBy());
    }


    @Override
    public int getTotalItemByCondition(CategoriesBuilder categoriesBuilder) {
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM categories WHERE 1=1");
        sql = buildQueryCommon(sql, categoriesBuilder);
        return count(sql.toString(), categoriesBuilder.getCreatedBy());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        update(sql, id);
    }

    private StringBuilder buildQueryCommon(StringBuilder sql, CategoriesBuilder categoriesBuilder) {
        if(categoriesBuilder.getCategory()!= null &&
                StringUtils.isNotBlank(categoriesBuilder.getCategory())) {
            sql.append(" AND category LIKE '%"+categoriesBuilder.getCategory()+"%'");
        }
        if(categoriesBuilder.getCreatedBy() != null &&
                StringUtils.isNotBlank(categoriesBuilder.getCreatedBy())) {
            sql.append(" AND createdby = ?");
        }
        return sql;
    }
}
