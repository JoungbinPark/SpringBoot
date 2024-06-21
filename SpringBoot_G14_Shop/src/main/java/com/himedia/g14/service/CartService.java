package com.himedia.g14.service;

import com.himedia.g14.dao.ICartDao;
import com.himedia.g14.dto.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CartService {

    @Autowired
    ICartDao cdao;


    public void insertCart(CartVO cvo) {
        cdao.insertCart(cvo);
    }

    public HashMap<String, Object> getCartList(String userid) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<CartVO> list = cdao.getCartList(userid);
        result.put("cartList", list);
        int totalPrice = 0;
        for( CartVO cart : list)
            totalPrice += (cart.getPrice2()*cart.getQuantity() );
        result.put("totalPrice", totalPrice);



        return result;
    }

    public void deleteCart(String[] cseqs) {
        for(String cseq : cseqs){
            cdao.deleteCart( Integer.parseInt(cseq));
        }
    }
}
