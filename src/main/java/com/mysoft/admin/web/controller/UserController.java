package com.mysoft.admin.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 3:16 PM
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/dataCapture")
public class UserController {

    @RequestMapping("/get")
    @ResponseBody
    public String get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("F001"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),
                authorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "ss";
    }

    /**
     * 列表权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('DATACAPTURE_INIT_LIST')")
    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        return "dataCapture-list-api";
    }

    /**
     * 录入权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('DATACAPTURE_DETAIL_INPUT')")
    @RequestMapping("/input")
    @ResponseBody
    public String input() {
        return "dataCapture-input-api";
    }

    /**
     * 编辑权限
     *
     * @return
     */
    @PreAuthorize("hasPermission('xxx', 'DATACAPTURE_DETAIL_EDIT')")
    @RequestMapping("/edit")
    public String edit() {
        return "first";
    }
}
