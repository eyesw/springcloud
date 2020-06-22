package com.aaa.springboot_test_20200619.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.aaa.springboot_test_20200619.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager webSecurityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    /**
     * 自定义的Realm
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm=new UserRealm();
       userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    /**
     * 密码匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(false);
        return credentialsMatcher;
    }

    /**
     * 调用初始化方法和销毁方法
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor processor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注解识别
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator(){
        DefaultAdvisorAutoProxyCreator autoProxyCreator=new DefaultAdvisorAutoProxyCreator() ;
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor() ;
        advisor.setSecurityManager(webSecurityManager());
        return advisor;
    }

    @Bean
    public ShiroFilterFactoryBean factoryBean(){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(webSecurityManager());
        factoryBean.setLoginUrl("/login.html");
        factoryBean.setSuccessUrl("/index.html");
        factoryBean.setUnauthorizedUrl("/error.html");

        //要过滤的路径  在map集合
        Map<String,String> map=new HashMap<>();
        map.put("/user/login","anon");
        map.put("/static/**","anon");
        map.put("/*.jar","anon");
        map.put("/logout","logout");
        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);

        return factoryBean;
    }
    /**
     * thymeleaf 页面使用 shiro标签
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
