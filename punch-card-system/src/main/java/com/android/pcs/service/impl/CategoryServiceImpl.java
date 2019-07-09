package com.android.pcs.service.impl;

import com.android.pcs.mapper.CategoryMapper;
import com.android.pcs.model.Category;
import com.android.pcs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }
}
