package com.github.wz2cool.elasticsearch.mapper;

import com.github.wz2cool.elasticsearch.query.DynamicQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;

/**
 * @author Frank
 * @date 2021/05/25
 **/
public interface DeleteByDynamicQueryMapper<T> {

    /**
     * delete by dynamic query
     *
     * @param elasticsearchOperations elasticsearch operations
     * @param dynamicQuery            dynamic query
     */
    default void deleteByDynamicQuery(ElasticsearchOperations elasticsearchOperations, DynamicQuery<T> dynamicQuery) {
        final QueryBuilder queryBuilder = dynamicQuery.buildQuery();
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(queryBuilder);
        elasticsearchOperations.delete(deleteQuery, dynamicQuery.getClazz());
    }
}
