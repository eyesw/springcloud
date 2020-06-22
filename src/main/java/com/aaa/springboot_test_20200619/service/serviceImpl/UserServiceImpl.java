package com.aaa.springboot_test_20200619.service.serviceImpl;

import com.aaa.springboot_test_20200619.entity.User;
import com.aaa.springboot_test_20200619.mapper.UserMapper;
import com.aaa.springboot_test_20200619.service.UserService;
import com.aaa.springboot_test_20200619.until.EnctypeUtil;
import com.aaa.springboot_test_20200619.until.ListToSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 删除
     * @param useied
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer useied) {
        return userMapper.deleteByPrimaryKey(useied);
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @Override
    public int insert(User record) {
        String encPassword = EnctypeUtil.encPassword(record.getPassword(), record.getUsername());
        record.setPassword(encPassword);
        return userMapper.insert(record);
    }

    /**
     * 根据id查询
     * @param useied
     * @return
     */
    @Override
    public User selectByPrimaryKey(Integer useied) {
        return userMapper.selectByPrimaryKey(useied);
    }

    /**
     * 查询
     * @return
     */
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 模糊查询
     * @param user
     * @return
     */
    @Override
    public List<User> selectById(User user) {
        return userMapper.selectById(user);
    }

    /**
     * 登录
     * @param username
     * @return
     */
    @Override
    public User findByLogin(String username) {
        return userMapper.findByLogin(username);
    }

    /**
     * 角色
     * @param username
     * @return
     */
    @Override
    public Set<String> selectAllRole(String username) {
        List<String> role = userMapper.selectAllRole(username);
        return ListToSet.changeListToSet(role);
    }

    /**
     * 查询角色对应的权限
     * @param username
     * @return
     */
    @Override
    public Set<String> selectAllModule(String username) {
        List<String> module = userMapper.selectAllModule(username);
        return ListToSet.changeListToSet(module);
    }

}
