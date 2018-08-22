package com.penguin.find.seekhoney.mapper;

import com.penguin.find.seekhoney.model.Product;

import java.util.List;

/**
 * 产品CRUD
 */
public interface ProductMapper {
    /**
     * 新增产品
     * @param product 产品
     */
    void insert(Product product);

    /**
     * 查询所有产品
     * @return 产品列表
     */
    List getAll();

    /**
     * 更新产品信息
     * @param product 产品
     */
    void update(Product product);

    /**
     * 删除产品
     * @param name 产品名称
     */
    void delete(String name);
}
