package com.mysoft.admin.security;

import com.mysoft.admin.bean.HrRoleBean;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 3:51 PM
 * @Version 1.0
 */
public class HrRoleBeanUtil {

    public static HrRoleBean getCurrentHr() {
        return (HrRoleBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
