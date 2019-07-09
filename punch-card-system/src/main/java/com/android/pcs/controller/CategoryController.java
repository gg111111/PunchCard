package com.android.pcs.controller;

import com.android.pcs.model.Category;
import com.android.pcs.service.CategoryService;
import com.android.pcs.util.ResultUtil;
import com.android.pcs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categoryList")
    public ResultVO categoryList() {
        List<Category> categoryList = categoryService.getCategoryList();
        return ResultUtil.success(categoryList);
    }

}
