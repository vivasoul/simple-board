package com.odth.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<CategoryVO> getCategory() {

        return categoryMapper.selectCategory();
    }
}
