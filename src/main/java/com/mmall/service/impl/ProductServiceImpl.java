package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加/更新商品
     * @param product
     * @return
     */
    @Override
    public Msg saveProduct(Product product) {
        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            Date time = new Date();
            int rowCount = 0;

            if(product.getId() != null){
                product.setUpdateTime(time);
                rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0){
                    return Msg.createSucMsg(Result.UPDATE_PRODUCT_SUCCESS);
                }
            }else{
                product.setUpdateTime(time);
                product.setCreateTime(time);
                rowCount = productMapper.insert(product);
                if(rowCount > 0){
                    return Msg.createSucMsg(Result.SAVE_PRODUCT_SUCCESS);
                }
            }

        }
        return Msg.createSucMsg(Result.SAVE_PRODUCT_FAIL);
    }

    /**
     * 修改商品销售状态
     * @param productId
     * @param status
     * @return
     */
    public Msg setSaleStatus(Integer productId, Integer status){
        if(productId == null || status == null){
            return Msg.createFailMsg(Result.PRODUCT_OR_STATUS_IS_NULL);
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return Msg.createSucMsg(Result.UPDATE_PRODUCT_STATUS_SUCCESS);
        }
        return Msg.createFailMsg(Result.UPDATE_PRODUCT_STATUS_FAIL);
    }

    /**
     * 管理商品详情
     * @param productId
     * @return
     */
    public Msg manageProductDetail(Integer productId){
        if(productId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return Msg.createSucMsg(productDetailVo);
    }

    /**
     * 获取商品列表
     * @param productName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Msg listProducts(String productName, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuffer().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectList(productName);
        List<ProductListVo> productListVos = Lists.newArrayList();
        for(Product product : productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVos.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVos);
        return Msg.createSucMsg(pageInfo);
    }

    @Override
    public Msg getProductDetail(Integer productId) {
        if(productId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode()){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return Msg.createSucMsg(productDetailVo);
    }

    @Override
    public Msg getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        if(categoryId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return Msg.createSucMsg(pageInfo);
            }
            categoryIdList = (List<Integer>) iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product product : productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return Msg.createSucMsg(pageInfo);
    }

    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }

    private ProductListVo assembleProductListVo(Product product){
        ProductListVo productListVo = new ProductListVo();
        BeanUtils.copyProperties(product, productListVo);
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        return productListVo;
    }
}
