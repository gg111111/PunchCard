package com.android.pcs.mapper;

import com.android.pcs.BaseTest;
import com.android.pcs.model.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryMapperTest extends BaseTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void getCategoryList() {
        List<Category> categoryList = categoryMapper.getCategoryList();
        System.out.println(categoryList);
    }
}