package com.github.wz2cool.elasticsearch.mapper;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DynamicQueryMapper<T, ID> extends ElasticsearchRepository<T, ID>,
        SelectByDynamicQueryMapper<T>, SelectByLogicPagingQueryMapper<T>, DeleteByDynamicQueryMapper<T> {
}
