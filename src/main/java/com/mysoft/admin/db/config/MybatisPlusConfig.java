package com.mysoft.admin.db.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 *
 * @auth lnsof
 * @Date 2018/9/12 20:56
 * @Version 1.0
 */
@Configuration
@MapperScan("com.mysoft.admin.db.mapper*")
public class MybatisPlusConfig {

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

}
