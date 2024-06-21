package com.himedia.g14.controller;

import com.himedia.g14.dto.CartVO;
import com.himedia.g14.dto.MemberVO;
import com.himedia.g14.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class CartController {

    @Autowired
    CartService cs;

    @PostMapping("/cartInsert")
    public String cartInsert(@RequestParam("pseq") int pseq,
                             @RequestParam("quantity") int quantity,
                             HttpServletRequest request,
                             Model model){

        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if(loginUser == null){
            return "redirect:/loginForm";
        } else {
            CartVO cvo = new CartVO();
            cvo.setPseq(pseq);
            cvo.setQuantity(quantity);
            cvo.setUserid(loginUser.getUserid());
            cs.insertCart(cvo);
            return "redirect:/cartList";
        }
    }

    @GetMapping("/cartList")
    public ModelAndView cartList(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if(loginUser==null){
            mav.setViewName("redirect:/login");
        } else{
            HashMap<String, Object> result = cs.getCartList(loginUser.getUserid());
            mav.addObject("cartList", result.get("cartList"));
            mav.addObject("totalPrice", result.get("totalPrice"));
            mav.setViewName("mypage/cartList");
        }

        return mav;
    }

    @PostMapping("/cartDelete")
    public String cartDelete(@RequestParam("cseq") String[] cseqs ){
        cs.deleteCart(cseqs);
        return "redirect:/cartList";
    }
}
