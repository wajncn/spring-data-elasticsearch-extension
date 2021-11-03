package com.github.wz2cool.elasticsearch.cache;

import com.github.wz2cool.elasticsearch.helper.ReflectHelper;
import com.github.wz2cool.elasticsearch.model.ColumnInfo;
import com.github.wz2cool.exception.PropertyNotFoundInternalException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class EntityCache {

    private static final Map<Class, Map<String, ColumnInfo>> COLUMN_INFO_CACHE_MAP = new ConcurrentHashMap<>();

    /// region implement singleton.

    private static EntityCache instance = new EntityCache();

    private EntityCache() {
    }

    public static EntityCache getInstance() {
        return instance;
    }

    /// endregion

    public ColumnInfo getColumnInfo(Class clazz, String propertyName) {
        Map<String, ColumnInfo> propertyDbColumnMap = COLUMN_INFO_CACHE_MAP.get(clazz);
        if (Objects.isNull(propertyDbColumnMap)) {
            cacheColumnInfo(clazz);
            propertyDbColumnMap = COLUMN_INFO_CACHE_MAP.get(clazz);
        }
        if (!propertyDbColumnMap.containsKey(propertyName)) {
            throw new PropertyNotFoundInternalException(String.format("Can't found property: %s", propertyName));
        }

        return propertyDbColumnMap.get(propertyName);
    }

    private void cacheColumnInfo(Class clazz) {
        Field[] properties = ReflectHelper.getProperties(clazz);
        Map<String, ColumnInfo> map = new ConcurrentHashMap<>();
        for (Field field : properties) {
            field.setAccessible(true);
            final String propertyName = field.getName();
            final String columnName = getColumnNameByProperty(field);
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setField(field);
            columnInfo.setColumnName(columnName);
            columnInfo.setPropertyName(propertyName);
            map.put(propertyName, columnInfo);
        }
        COLUMN_INFO_CACHE_MAP.put(clazz, map);
    }

    public String getColumnNameByProperty(Field field) {
        if (field.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class)) {
            final String alias = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class).name();
            if (StringUtils.isNotBlank(alias)) {
                return alias;
            } else {
                return field.getName();
            }
        }
        return field.getName();
    }
}
