package com.himedia.g14.dao;

import com.himedia.g14.dto.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductDao {

    List<ProductVO> getBestList();
    List<ProductVO> getNewList();
    List<ProductVO> getKindList(String kind);

    ProductVO getProduct(int pseq);
}
