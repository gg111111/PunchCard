package com.android.pcs.mapper;

import com.android.pcs.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    /**
     * 获取所有分类
     * @return
     */
    List<Category> getCategoryList();

}
