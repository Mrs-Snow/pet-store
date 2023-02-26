package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface UserService extends IService<User> {
   User login(String username,String password);
   int register(User user);

   boolean verify(User user);

   int updatePassword(String old,String newPassword,String username);
   String updatePassword(String old,String newPassword,Long userId);
}
