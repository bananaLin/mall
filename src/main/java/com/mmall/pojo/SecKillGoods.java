package com.mmall.pojo;

public class SecKillGoods {

    private int id;
    private int goodsId;
    private int seckillId;
    private long seckillPrice;
    private int order;
    private int maxBuy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public long getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(long seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getMaxBuy() {
        return maxBuy;
    }

    public void setMaxBuy(int maxBuy) {
        this.maxBuy = maxBuy;
    }

    @Override
    public String toString() {
        return "SecKillGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", seckillId=" + seckillId +
                ", seckillPrice=" + seckillPrice +
                ", order=" + order +
                ", maxBuy=" + maxBuy +
                '}';
    }
}
