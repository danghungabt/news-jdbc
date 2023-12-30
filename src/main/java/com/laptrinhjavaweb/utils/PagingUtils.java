package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.paging.Pageable;
import org.apache.commons.lang.StringUtils;

public class PagingUtils {
    public static StringBuilder pagingQuery(Pageable pageable, StringBuilder sql) {
        if (pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName())
                && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
        }

        return sql;
    }
}
