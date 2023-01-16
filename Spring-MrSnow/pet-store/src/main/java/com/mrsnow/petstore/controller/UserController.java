package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.dao.User;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import com.mrsnow.petstore.service.UserService;
import com.mrsnow.petstore.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public R<Map<String,Object>> login(@RequestBody User user, HttpServletResponse response){
        String userName = user.getUserName();
        String password = user.getPassword();
        User login = userService.login(userName, password);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",login);
        map.put("token",TokenUtil.sign(userName,password));
        if (login!=null){
            return R.success(map,"登录成功！");
        }else {
            return R.fail("登录失败，请检查用户名和密码是否正确！");
        }
    }

    @PostMapping(value = "/register")
    public R<Integer> register(@RequestBody User user, HttpServletResponse response){
        int register = userService.register(user);
        if(register==10){
            return R.fail("注册失败，此用户名已有人使用");
        }
        if(register==20){
            return R.fail("注册失败，此手机号已注册");
        }
        if (register==1){
            return R.success(register,"注册成功！");
        }else {
            return R.fail("注册失败，请检查注册信息");
        }
    }

    @PostMapping(value = "/findPassword")
    public R verify(@RequestBody User user){
        if(userService.verify(user)){
            return R.success("验证成功");
        } else {
            return R.fail("验证失败");
        }
    }

    @PostMapping(value = "/findPassword/updatePassword")
    public R updatePassword(@RequestBody JO<Map<String ,String>> jo){
        String old = jo.getData().get("old");
        String newPassword = jo.getData().get("new");
        if (!old.equals(newPassword)){
            return R.fail("两次密码不一致");
        }
        String username = jo.getData().get("username");
        int i = userService.updatePassword(old, newPassword, username);
        if (i==10){
            return R.fail("新密码不能是旧密码！");
        }
        if (i==0){
            return R.success("修改密码成功！");
        }
        return R.fail("服务器开小差了！");
    }
    @GetMapping (value = "/testHeader")
    public R testHeader() {
        return R.success("测试成功！");
    }
}

