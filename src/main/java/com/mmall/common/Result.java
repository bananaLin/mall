package com.mmall.common;

public enum Result implements Labeled{

    FAIL(-1, "失败"),

    SUCCESS(0, "成功"),

    NOT_ALLOW(1, "无权操作"),

    ERROR_PARAMETER(2, "参数错误"),

    CREATE_FAIL(3, "创建失败"),

    CREATE_SUCCESS(4, "创建成功"),

    UPDATE_FAIL(5, "更新失败"),

    UPDATE_SUCCESS(6, "更新成功"),

    // 用户模块 1000 - 1999

    // 商品模块 2000 - 2999
    PRODUCT_NOT_ALLOW_NULL(2000, "商品不能为空"),

    SAVE_PRODUCT_SUCCESS(2001, "添加商品成功"),

    SAVE_PRODUCT_FAIL(2002, "添加商品失败"),

    UPDATE_PRODUCT_SUCCESS(2003, "更新商品成功"),

    PRODUCT_OR_STATUS_IS_NULL(2004, "商品ID或状态为空"),

    UPDATE_PRODUCT_STATUS_SUCCESS(2005, "修改商品销售状态成功"),

    UPDATE_PRODUCT_STATUS_FAIL(2006, "修改商品销售状态失败"),

    NO_PRODUCT(2007, "商品不存在"),

    // 购物车模块 3000 - 3999
    ADD_CART_SUCCESS(3000, "添加购物车成功"),

    // 收货地址 4000 - 4999
    ADD_SHIPPING_FAIL(4000, "添加收货地址失败"),

    UPDATE_SHIPPING_FAIL(4001, "更新收货地址失败"),

    SHIPPING_USER_ID_COULD_BE_NULL(4002, "收货人ID不能为空"),

    SHIPPING_COULD_BE_NULL(4003, "收货地址不能为空"),
    ;

    private Integer code;
    private String label;

    @Override
    public String getLabel() {
        return label;
    }

    Result(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }
}
