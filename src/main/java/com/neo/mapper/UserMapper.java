package com.neo.mapper;


import com.neo.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String idcard);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String idcard);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    Long getUserCount();
}