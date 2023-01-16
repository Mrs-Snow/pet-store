package com.mrsnow.petstore;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrsnow.petstore.dao.User;
import com.mrsnow.petstore.mapper.UserMapper;
import com.mrsnow.petstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PetStoreApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void test2(){
        User user = userService.getOne(new QueryWrapper<>());
        System.out.println(user.getMobile());
    }
    @Test
    void test1(){
        User user = userMapper.selectOne(new QueryWrapper<>());
        System.out.println(user.getMobile());
    }

    @Test
    void test3(){
        User user = new User();
        user.setUserName("test001");
        user.setPassword("123456");
        user.setEmail("112233@none.com");
        user.setMobile("18838938920");
        System.out.println(userService.register(user));
    }
}
