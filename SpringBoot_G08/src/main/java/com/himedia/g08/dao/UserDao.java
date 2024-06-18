package com.himedia.g08.dao;

import com.himedia.g08.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    //Connection con = null;
    //PreparedStatement pstmt = null;
    //ResultSet rs = null;

    //application.properties에 있는 DB 연결정보로 연결되어 있는 JdbcTemplate이 스프링컨테이너에 이미 넣어져 있습니다.
    //JdbcTemplate는 Springboot가 미리 스프링 컨테이너에 bean으로 미리 보관되고 시작합니다.

    @Autowired
    private JdbcTemplate template;


    public List<UserDto> selectAll() {
        String sql = "select * from myuser";
        List<UserDto> list = template.query(
                sql,
                new BeanPropertyRowMapper<UserDto>(UserDto.class));
        return list;

    }
}
