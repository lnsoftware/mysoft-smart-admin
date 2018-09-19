package com.mysoft.admin.web.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class UserController {


    @RequestMapping("/get")
    public String get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("F001"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),
                authorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "ss";
    }

    @RequestMapping("/test")
    public String test() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        Object credentials = auth.getCredentials();
        Object details = auth.getDetails();
        Object principal = auth.getPrincipal();
        return "tet";
    }
}
