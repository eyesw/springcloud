package com.aaa.springboot_test_20200619.mapper;


import com.aaa.springboot_test_20200619.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer useied);

    int insert(User record);

    User selectByPrimaryKey(Integer useied);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User findByName(String username);

    /**
     * 模糊查询
     */
    List<User> selectById(User user);




    /**
     * 根据名字查询 （登陆）
     * @param username
     * @return
     */
    @Select("select * from tb_user where username=#{username}")
    public User findByLogin(String username);

    /**
     * 根据用户名查询用户所对应的所有角色
     * @param username
     * @return
     */
    @Select("select r.rolename from tb_user u, role r where u.roleid=r.roleid and u.username=#{username}")
    public List<String> selectAllRole(String username);

    /**
     * 根据用户名查询所有的权限
     * @param username
     * @return
     */
    @Select("select r.quanxian from tb_user u, role r where u.roleid=r.roleid and u.username=#{username}")
    public List<String> selectAllModule(String username);
}