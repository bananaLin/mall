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

    // 用户模块 100 - 199

    // 商品模块 200 - 299
    PRODUCT_NOT_ALLOW_NULL(200, "商品不能为空"),

    SAVE_PRODUCT_SUCCESS(201, "添加商品成功"),

    SAVE_PRODUCT_FAIL(202, "添加商品失败"),

    UPDATE_PRODUCT_SUCCESS(203, "更新商品成功"),

    PRODUCT_OR_STATUS_IS_NULL(204, "商品ID或状态为空"),

    UPDATE_PRODUCT_STATUS_SUCCESS(205, "修改商品销售状态成功"),

    UPDATE_PRODUCT_STATUS_FAIL(206, "修改商品销售状态失败"),

    NO_PRODUCT(207, "商品不存在"),

    //购物车模块 300 - 399
    ADD_CART_SUCCESS(300, "添加购物车成功"),

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
