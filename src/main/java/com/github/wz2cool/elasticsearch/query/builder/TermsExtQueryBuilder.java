package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetArrayPropertyFunction;
import com.github.wz2cool.elasticsearch.lambda.GetPropertyFunction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Frank
 * @see TermsExtQueryBuilder
 */
public class TermsExtQueryBuilder<T, R extends Comparable> implements ExtQueryBuilder {

    private TermsQueryBuilder termsQueryBuilder;

    /**
     * @see TermsQueryBuilder#TermsQueryBuilder(String, Iterable)
     */
    @SafeVarargs
    public TermsExtQueryBuilder(GetPropertyFunction<T, R> getPropertyFunc, R... values) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.termsQueryBuilder = new TermsQueryBuilder(propertyName,
                Arrays.stream(values).map(this::getFilterValue).collect(Collectors.toList()));
    }

    /**
     * @see TermsQueryBuilder#TermsQueryBuilder(String, Iterable)
     */
    @SafeVarargs
    public TermsExtQueryBuilder(GetArrayPropertyFunction<T, R> getPropertyFunc, R... values) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.termsQueryBuilder = new TermsQueryBuilder(propertyName,
                Arrays.stream(values).map(this::getFilterValue).collect(Collectors.toList()));
    }

    @Override
    public QueryBuilder build() {
        return this.termsQueryBuilder;
    }
}
