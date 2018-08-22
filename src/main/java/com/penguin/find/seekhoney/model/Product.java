package com.penguin.find.seekhoney.model;

import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 产品
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1981566879875628910L;

    /**
     * 产品ID
     */
    private int id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格
     */
    private String price;

    /**
     * 产品库存数量
     */
    private int count;

    /**
     * 产品类别
     */
    private String category;

    /**
     * 产品规格
     */
    private String size;

    /**
     * 产品包装
     */
    private String packing;

    /**
     * 生产日期
     */
    private String productionDate;

    /**
     * 保质期
     */
    private String shelfLife;

    /**
     * 图片URL
     */
    private String imgUrl;

    /**
     * 填充产品信息
     * @param info
     */
    public void setInfo(Map info) {
        this.id = MapUtils.getIntValue(info, "id");
        this.name = MapUtils.getString(info, "name");
        this.price = MapUtils.getString(info, "price");
        this.count = MapUtils.getIntValue(info, "count");
        this.category = MapUtils.getString(info, "category");
        this.size = MapUtils.getString(info, "size");
        this.packing = MapUtils.getString(info, "packing");
        this.productionDate = MapUtils.getString(info, "productionDate");
        this.shelfLife = MapUtils.getString(info, "shelfLife");
        this.imgUrl = MapUtils.getString(info, "imgUrl");
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
