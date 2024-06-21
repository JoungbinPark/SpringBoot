package com.himedia.g14.controller;

import com.himedia.g14.dto.MemberVO;
import com.himedia.g14.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class OrderController {

    @Autowired
    OrderService os;

    @PostMapping("/orderInsert")
    public String orderInsert(@RequestParam("cseq") String [] cseqs, HttpServletRequest request){
        int oseq = 0;
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        if(loginUser == null){
            return "member/login";
        } else {
            oseq = os.insertOrder( cseqs, loginUser.getUserid() );
        }
        return "redirect:/orderList?oseq="+oseq;
    }

    @GetMapping("orderList")
    public ModelAndView orderList(@RequestParam("oseq") int oseq, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        if( loginUser == null)
            mav.setViewName("member/login");
        else{
            HashMap<String, Object> result = os.getOrderByOseq(oseq);
            mav.addObject("orderList", result.get("orderList"));
            mav.addObject("totalPrice", result.get("totalPrice"));
            mav.setViewName("mypage/orderList");
        }
        return mav;
    }

    @PostMapping("orderInsertOne")
    public String orderInsertOne(@RequestParam("pseq") int pseq,
                                 @RequestParam("quantity") int quantity,
                                 HttpServletRequest request){
        int oseq = 0;
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        if( loginUser == null){
            return "member/login";
        }
        else{
            oseq = os.insertOrderOne(pseq, quantity, loginUser.getUserid());
        }
        return "redirect:/orderList?oseq="+oseq;
    }

    @GetMapping("/mypage")
    public ModelAndView mypage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        if( loginUser == null)
            mav.setViewName("member/login");
        else{
            mav.setViewName("mypage/mypage");
            mav.addObject("orderList", os.myPageList(loginUser.getUserid()));
            mav.addObject("title", "진행중인 주문내역");
        }
        return mav;
    }

    @GetMapping("/orderAll")
    public ModelAndView orderAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        if( loginUser == null)
            mav.setViewName("member/login");
        else{
            mav.setViewName("mypage/mypage");
            mav.addObject("orderList", os.orderAllList(loginUser.getUserid()));
            mav.addObject("title", "총 주문내역");
        }
        return mav;
    }
}
