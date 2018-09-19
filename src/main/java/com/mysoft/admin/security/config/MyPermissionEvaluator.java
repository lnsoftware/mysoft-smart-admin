package com.mysoft.admin.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/19/2018 2:52 PM
 * @Version 1.0
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {

    /**
     * @param authentication     represents the user in question. Should not be null.
     * @param targetDomainObject the domain object for which permissions should be
     *                           checked. May be null in which case implementations should return false, as the null
     *                           condition can be checked explicitly in the expression.
     * @param permission         a representation of the permission object as supplied by the
     *                           expression system. Not null.
     * @return true if the permission is granted, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return hasPermission(authentication, permission);
    }

    /**
     * Alternative method for evaluating a permission where only the identifier of the
     * target object is available, rather than the target instance itself.
     *
     * @param authentication represents the user in question. Should not be null.
     * @param targetId       the identifier for the object instance (usually a Long)
     * @param targetType     a String representing the target's type (usually a Java
     *                       classname). Not null.
     * @param permission     a representation of the permission object as supplied by the
     *                       expression system. Not null.
     * @return true if the permission is granted, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }


    /**
     * 简单比较如果字符串相同则有权限
     *
     * @param authentication
     * @param permission
     * @return
     */
    private boolean hasPermission(Authentication authentication, Object permission) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
