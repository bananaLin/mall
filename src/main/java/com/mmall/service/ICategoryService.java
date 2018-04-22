package com.mmall.service;

import com.mmall.common.Msg;

public interface ICategoryService {

    Msg addCategory(String categoryName, Integer parentId);

    Msg getChildrenParallelCategory(Integer CategoryId);

    Msg updateCategoryName(Integer categoryId,String categoryName);

    Msg selectCategoryAndChildrenById(Integer categoryId);
}
