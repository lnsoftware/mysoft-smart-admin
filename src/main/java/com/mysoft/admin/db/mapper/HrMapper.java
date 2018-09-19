package com.mysoft.admin.db.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysoft.admin.bean.HrBean;
import com.mysoft.admin.bean.HrRoleBean;
import com.mysoft.admin.db.entity.Hr;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yang.m.zhang
 * @since 2018-09-18
 */
public interface HrMapper extends BaseMapper<Hr> {

    /**
     * 获取用户以及用户角色信息
     *
     * @param hrBean
     * @return
     */
    public List<HrRoleBean> getHrRole(HrBean hrBean);

}
