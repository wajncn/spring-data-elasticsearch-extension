package com.github.wz2cool.elasticsearch.mapper;

import com.github.wz2cool.elasticsearch.model.LogicPagingResult;
import com.github.wz2cool.elasticsearch.query.DynamicQuery;
import com.github.wz2cool.elasticsearch.query.LogicPagingQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

import java.util.List;

public class DynamicQueryMapperImpl<T, ID> extends SimpleElasticsearchRepository<T, ID> implements DynamicQueryMapper<T, ID> {

    public DynamicQueryMapperImpl() {
    }

    public DynamicQueryMapperImpl(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    public DynamicQueryMapperImpl(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }

    @Override
    public LogicPagingResult<T> selectByLogicPaging(LogicPagingQuery<T> logicPagingQuery) {
        return selectByLogicPaging(elasticsearchOperations, logicPagingQuery);
    }

    @Override
    public List<T> selectByDynamicQuery(DynamicQuery<T> dynamicQuery) {
        return selectByDynamicQuery(elasticsearchOperations, dynamicQuery);
    }
}
