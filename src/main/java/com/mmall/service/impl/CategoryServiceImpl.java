package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param categoryName
     * @param parentId
     * @return
     */
    public Msg addCategory(String categoryName, Integer parentId){

        logger.info("categoryName:" + categoryName, "parentId:" + parentId);

        if(parentId == null || StringUtils.isBlank(categoryName)){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }

        Category category = new Category();
        Date time = new Date();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        category.setCreateTime(time);
        category.setUpdateTime(time);

        int rowCount = categoryMapper.insert(category);

        if(rowCount > 0){
            return Msg.createSucMsg(Result.CREATE_SUCCESS);
        }
        return Msg.createFailMsg(Result.CREATE_FAIL);
    }

    /**
     * 更新分类
     * @param categoryId
     * @param categoryName
     * @return
     */
    @Override
    public Msg updateCategoryName(Integer categoryId, String categoryName) {
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return Msg.createSucMsg(Result.UPDATE_SUCCESS);
        }
        return Msg.createFailMsg(Result.UPDATE_FAIL);
    }

    /**
     * 获取分类下的子节点
     * @param CategoryId
     * @return
     */
    @Override
    public Msg getChildrenParallelCategory(Integer CategoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(CategoryId);
        return Msg.createSucMsg(categoryList);
    }

    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    public Msg selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return Msg.createSucMsg(categoryIdList);
    }


    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet ,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem : categoryList){
            if(categoryItem != null){
                findChildCategory(categorySet, categoryItem.getId());
            }
        }
        return categorySet;
    }
}
