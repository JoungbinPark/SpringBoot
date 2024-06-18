package com.himedia.g10.dao;

import com.himedia.g10.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {
    public List<UserDto> select();
}
