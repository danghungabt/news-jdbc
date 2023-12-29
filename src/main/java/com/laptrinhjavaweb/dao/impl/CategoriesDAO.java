package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.mapper.CategoriesMapper;
import com.laptrinhjavaweb.model.CategoriesModel;
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
    public CategoriesModel findOne(Long id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoriesModel> categoriesModels = query(sql, new CategoriesMapper(), id);
        return categoriesModels.isEmpty() ? null : categoriesModels.get(0);
    }

    @Override
    public List<CategoriesModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories");
        /*if (pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName())
                && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
        }*/

        return query(sql.toString(), new CategoriesMapper());
    }

    @Override
    public CategoriesModel findOneBySlug(String slugCategory) {
        String sql = "SELECT * FROM categories WHERE slugcategory = ?";
        List<CategoriesModel> categoriesModels = query(sql, new CategoriesMapper(), slugCategory);
        return categoriesModels.isEmpty() ? null : categoriesModels.get(0);
    }
}
