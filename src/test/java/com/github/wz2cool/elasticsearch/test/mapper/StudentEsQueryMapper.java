package com.github.wz2cool.elasticsearch.test.mapper;

import com.github.wz2cool.elasticsearch.mapper.DynamicQueryMapper;
import com.github.wz2cool.elasticsearch.test.model.StudentES;

public interface StudentEsQueryMapper extends DynamicQueryMapper<StudentES, Long> {
}
