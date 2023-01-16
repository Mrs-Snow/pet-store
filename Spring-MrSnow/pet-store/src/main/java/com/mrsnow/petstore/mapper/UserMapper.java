package com.mrsnow.petstore.mapper;

import com.mrsnow.petstore.dao.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
