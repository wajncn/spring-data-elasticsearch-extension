package com.github.wz2cool.elasticsearch.test;

import com.github.wz2cool.elasticsearch.mapper.DynamicQueryMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(repositoryBaseClass = DynamicQueryMapperImpl.class)
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}