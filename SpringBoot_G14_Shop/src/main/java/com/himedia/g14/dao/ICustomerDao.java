package com.himedia.g14.dao;

import com.himedia.g14.dto.QnaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICustomerDao {
    List<QnaVO> getQnaList();
    QnaVO getQna(int qseq);

    void writeQna(String userid, String subject, String content, String secret, String pass);
}
