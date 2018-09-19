package com.mysoft.admin.db.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysoft.admin.bean.MenuBean;
import com.mysoft.admin.bean.MenuRoleBean;
import com.mysoft.admin.db.entity.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yang.m.zhang
 * @since 2018-09-18
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单以及菜单角色
     *
     * @param bean
     * @return
     */
    public List<MenuRoleBean> getMenuRole(MenuBean bean);
}
