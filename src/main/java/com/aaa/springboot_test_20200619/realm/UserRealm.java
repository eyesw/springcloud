package com.aaa.springboot_test_20200619.realm;



import com.aaa.springboot_test_20200619.entity.User;
import com.aaa.springboot_test_20200619.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User login = userService.findByLogin(username);
        if (login==null){
            throw new UnknownAccountException("用户"+login+"不存在");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(login.getUsername(),login.getPassword(), ByteSource.Util.bytes(login.getUsername()),getName());
        return info;
    }

    /**
     * 角色权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        //查询角色
        Set<String> role = userService.selectAllRole(username);
        System.out.println(role.toString());
        //查询权限
        Set<String> module = userService.selectAllModule(username);
        System.out.println(module.toString());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //绑定角色列表
        info.setRoles(role);
        //绑定权限列表
        info.setStringPermissions(module);
        return info;
    }


}
