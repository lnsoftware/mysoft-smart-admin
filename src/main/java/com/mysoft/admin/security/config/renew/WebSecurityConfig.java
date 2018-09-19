package com.mysoft.admin.security.config.renew;

import com.alibaba.fastjson.JSONObject;
import com.mysoft.admin.security.config.UrlAccessDecisionManager;
import com.mysoft.admin.security.config.UrlFilterInvocationSecurityMetadataSource;
import com.mysoft.admin.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/19/2018 10:28 AM
 * @Version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HrService hrService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()            // 请求权限配置
            .antMatchers("/assert/**", "/core/**", "/theme/**", "/biz/**", "/i18n/**")
            .permitAll()                    // 以上资源不需要权限
            .anyRequest()
            .authenticated()
        .and()
            .formLogin()                    // 定制登陆页面
            .loginPage("/login_page")       // 登陆页面
            .loginProcessingUrl("/login")   // 处理登陆Url
            .usernameParameter("username")  // 用户米参数
            .passwordParameter("password")  // 密码参数
            .failureUrl("/login_error")
            .permitAll()
            .successHandler(successHandler())
            .failureHandler(failureHandler())
        .and()
            .logout()                       // 定制注销行为
            .logoutUrl("/logout")           // 制定注销的URL路径
            .permitAll()                    // 注销请求可任意访问
//        .and()
//            .sessionManagement()            // session的创建策略，不会创建session，但会使用会话中已经存在的session
//            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//        .and()
//            .headers()
//            .frameOptions()
//            .disable()                      // 允许加载iframe
        .and()
            .exceptionHandling()            // 处理未认证用户，负责重定向到登录页面
            .accessDeniedHandler(accessDeniedHandler())
        .and()
            .csrf()
            .disable();                     // 关闭跨站请求伪造攻击
    }

    /**
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     *
     * @param registry
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        registry.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 未认证用户处理handler
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request,
                               HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("status", "error");
                json.put("message", "权限不足，请联系管理员!");
                out.write(json.toJSONString());
                out.flush();
                out.close();
            }
        };
    }

    /**
     * 登陆成功用户处理handler
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler () {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("status", "success");
                json.put("message", getPrincipal());
                out.write(json.toJSONString());
                out.flush();
                out.close();
            }
        };
    }

    /**
     * 获取登陆用户信息
     *
     * @return
     */
    private Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 用户登陆失败处理handler
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                StringBuffer sb = new StringBuffer();
                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                    sb.append("用户名或密码输入错误，登录失败!");
                } else if (e instanceof DisabledException) {
                    sb.append("账户被禁用，登录失败，请联系管理员!");
                } else {
                    sb.append("登录失败!");
                }
                JSONObject json = new JSONObject();
                json.put("status", "success");
                json.put("message", sb.toString());
                out.write(json.toJSONString());
                out.flush();
                out.close();
            }
        };
    }
}
