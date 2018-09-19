package com.mysoft.admin.bean;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 2:56 PM
 * @Version 1.0
 */
@Data
public class MenuRoleBean {

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Integer keepAlive;

    private Integer requireAuth;

    private Integer parentId;

    private Integer enabled;

    private List<RoleBean> roleList;
}
