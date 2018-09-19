package com.mysoft.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysoft.admin.bean.HrBean;
import com.mysoft.admin.bean.HrRoleBean;
import com.mysoft.admin.db.entity.Hr;
import com.mysoft.admin.db.mapper.HrMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 3:28 PM
 * @Version 1.0
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "   " + password);

        HrBean param = new HrBean();
        param.setUsername(s);
        List<HrRoleBean> list = hrMapper.getHrRole(param);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
