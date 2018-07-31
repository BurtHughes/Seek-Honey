package com.penguin.find.seekhoney.mapper;

import com.penguin.find.seekhoney.model.User;

/**
 * 用户CRUD
 */
public interface UserMapper {
    /**
     * 新增用户
     * @param user 用户对象
     */
    void insert(User user);

    /**
     * 查询用户(根据ID)
     * @param id 用户ID
     * @return
     */
    User getById(int id);

    /**
     * 查询用户(根据用户名)
     * @param name 用户名
     * @return
     */
    User getByName(String name);

    /**
     * 更新用户
     * @param user 用户对象
     */
    void update(User user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteById(int id);
}
