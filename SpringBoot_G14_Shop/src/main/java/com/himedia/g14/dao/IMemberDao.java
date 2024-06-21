package com.himedia.g14.dao;

import com.himedia.g14.dto.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {
    MemberVO getMember( String userid);
    void insertMember(MemberVO mvo);
}
