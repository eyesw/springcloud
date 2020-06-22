package com.aaa.springboot_test_20200619.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Integer useied;

    private String username;

    private Integer deptid;

    private String email;

    private String phonenum;

    private Integer status;

    private String password;

    private String salt;

    private Integer roleid;
}