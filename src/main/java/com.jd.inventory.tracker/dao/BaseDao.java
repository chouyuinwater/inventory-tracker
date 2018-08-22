package com.jd.inventory.tracker.dao;

import com.jd.inventory.tracker.domain.vo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T, R> {

    List<R> gets(@Param("page") Page page, @Param("po") T t);

    R get(T t);

    int save(R t);

    int update(R t);

    int updateByStatus(@Param("po") R t, @Param("oldStatus") Integer oldStatus);

    int delete(R t);
}
