package com.aaa.springboot_test_20200619.controller;

import com.aaa.springboot_test_20200619.entity.User;
import com.aaa.springboot_test_20200619.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询员工
     *
     * @return
     */
    @RequestMapping("/all")
    public String selectAll(Map map) {
        List<User> user = userService.selectAll();
        map.put("user", user);
        return "list";
    }

    /**
     * 添加员工
     *
     * @param record
     * @return
     */
    @RequiresRoles("总监")
    @RequiresPermissions("新增权限")
    @RequestMapping("/insert")
    public String insert(User record) {
        userService.insert(record);
        return "redirect:all";
    }

    /**
     * 根据id查询员工
     *
     * @param useied
     * @return
     */
    @RequestMapping("/queryId")
    public String selectByPrimaryKey(Map map,Integer useied) {
        User user = userService.selectByPrimaryKey(useied);
        map.put("user",user);
        return "update";
    }

    /**
     * 删除员工
     *
     * @param useied
     * @return
     */
    @RequiresRoles("总监")
    @RequiresPermissions("删除权限")
    @RequestMapping("/delete")
    public String deleteByPrimaryKey(Integer useied) {
        userService.deleteByPrimaryKey(useied);
        return "redirect:all";
    }

    /**
     * 修改员工
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    public String updateByPrimaryKey(User user) {
        userService.updateByPrimaryKey(user);
        return "redirect:all";
    }
    /**
     * 模糊查询
     */
    @RequestMapping("/selectById")
    public String selectById(Map map,User user) {
        List<User> users = userService.selectById(user);
        map.put("user",users);
        return "list";
    }

    /**
     * 登录
     * @param users
     * @return
     */
    @RequestMapping("/login")
    public String login(User users){
        Subject currentUser = SecurityUtils.getSubject();
        //当前用户还没有认证；没有登陆
        if (!currentUser.isAuthenticated()) {
            //用户名密码的号牌信息  从表单中传递过来的用户名 和 密码
            UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(),users.getPassword());
            //记住我
            token.setRememberMe(true);
            try {
                //调用登陆方法 将号牌委托给---->安全管理器---->进入认证器，调用Realm获取用户信息进行匹配
                currentUser.login(token);
                return "redirect:all";
            } catch (UnknownAccountException uae) {//账号不存在
                System.out.println("账号不存在");
            } catch (IncorrectCredentialsException ice) {//密码不匹配
                System.out.println("密码不匹配");
            } catch (LockedAccountException lae) {//账户锁定
                System.out.println("账户锁定");
            }
            catch (AuthenticationException ae) {
                System.out.println("认证异常");
            }
        }

        return "error";
    }
}
