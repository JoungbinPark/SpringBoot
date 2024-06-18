package com.himedia.g11.dao;

import com.himedia.g11.dto.BbsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBbsDao {
    public List<BbsDto> select();
    void insert(BbsDto bbsdto);
    BbsDto getBbs(int id);
    void update(BbsDto bbsdto);
    void delete(int id);
}
