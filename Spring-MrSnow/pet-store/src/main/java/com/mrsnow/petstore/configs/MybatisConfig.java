package com.mrsnow.petstore.configs;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页插件
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-11  16:30
 **/
@Configuration
public class MybatisConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }


}
