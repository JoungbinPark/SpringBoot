package com.himedia.g14.dao;

import com.himedia.g14.dto.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICartDao {
    void insertCart(CartVO cvo);

    List<CartVO> getCartList(String userid);

    void deleteCart(int i);
}
