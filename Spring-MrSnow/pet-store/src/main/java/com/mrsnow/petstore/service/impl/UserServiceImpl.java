package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.User;
import com.mrsnow.petstore.mapper.UserMapper;
import com.mrsnow.petstore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username).eq(User::getPassword,password);
        return getBaseMapper().selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(User user) {
        //用户输入的手机号
        String mobile = user.getMobile();
        //用户输入的用户名
        String userName = user.getUserName();
        //查询是否存在相同的用户名
        LambdaQueryWrapper<User> nameWrapper = new LambdaQueryWrapper<>();
        nameWrapper.eq(User::getUserName,userName);
        LambdaQueryWrapper<User> phoneWrapper = new LambdaQueryWrapper<>();
        //查询是否存在相同的手机号
        User user1 = getBaseMapper().selectOne(nameWrapper);
        phoneWrapper.eq(User::getMobile,mobile);
        User user2 = getBaseMapper().selectOne(phoneWrapper);
        //根据查询结果返回相应状态
        if(user1!=null){
            return 10;
        }
        if(user2!=null){
            return 20;
        }
        //配置默认昵称
        int count = count();
        String formattedNum = String.format("%03d", count);
        user.setNickName("用户"+formattedNum);
        //插入数据
        return getBaseMapper().insert(user);
    }

    @Override
    public boolean verify(User user){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> eq = wrapper.eq(User::getUserName, user.getUserName()).eq(User::getMobile, user.getMobile())
                .eq(User::getEmail, user.getEmail());
        User user1 = getBaseMapper().selectOne(eq);
        return user1 != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePassword(String old, String newPassword,String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> eq = wrapper.eq(User::getUserName, username);
        User user = getBaseMapper().selectOne(eq);
        String oldPassword = user.getPassword();
        if (oldPassword.equals(newPassword)){
            return 10;
        }else {
            user.setPassword(newPassword);
            updateById(user);
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updatePassword(String old, String newPassword,Long userId) {
        User user = getById(userId);
        if(!old.equals(user.getPassword())){
            return "旧密码不正确！";
        }
        if(user.getPassword().equals(old)){
            return "新密码不能是旧密码！";
        }
        user.setPassword(newPassword);
        updateById(user);
        return "修改密码成功！";
    }
}
