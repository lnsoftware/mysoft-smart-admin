package com.mysoft.admin.service;

import com.mysoft.admin.bean.MenuBean;
import com.mysoft.admin.bean.MenuRoleBean;
import com.mysoft.admin.db.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 3:28 PM
 * @Version 1.0
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<MenuRoleBean> getAllMenu() {
        MenuBean param = new MenuBean();
        return menuMapper.getMenuRole(param);
    }

}
