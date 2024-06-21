package com.himedia.g14.dao;

import com.himedia.g14.dto.CartVO;
import com.himedia.g14.dto.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOrderDao {

    void insertOrders(String userid);
    int lookupMaxOseq();
    CartVO getCart(int cseq);
    void insertOrderDetail(int oseq, CartVO cvo);

    List<OrderVO> getOrderByOseq(int oseq);

    void insertOrderDetailOne(int oseq, int pseq, int quantity);

    List<Integer> getOseqListIng(String userid);

    List<Integer> getOseqListAll(String userid);
}
