package com.aaa.springboot_test_20200619.service;

import com.aaa.springboot_test_20200619.entity.User;


import java.util.List;
import java.util.Set;

public interface UserService {
    int deleteByPrimaryKey(Integer useied);

    int insert(User record);

    User selectByPrimaryKey(Integer useied);

    List<User> selectAll();

    int updateByPrimaryKey(User record);



    /**
            * 模糊查查
     * @return
             */
    List<User> selectById(User user);

    /**
     * 根据名字查询 （登陆）
     * @param username
     * @return
     */
    public User findByLogin(String username);
    /**
     * 根据用户名查询用户所对应的所有角色
     * @param username
     * @return
     */
    public Set<String> selectAllRole(String username);
    /**
     * 根据用户名查询所有的权限
     * @param username
     * @return
     */
    public Set<String> selectAllModule(String username);

}
