package com.himedia.g12.controller;

import com.himedia.g12.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @Autowired
    MyService ms;

    @GetMapping("/")
    public String root(){
        return "buy_ticket";
    }

    @GetMapping("/buyTicketCard")
    public String buyTicketCard(
            @RequestParam("id") String id,
            @RequestParam("amount") int amount,
            @RequestParam("error") int error,
            Model model){

        //전달받은 파라미터들을 해당 테이블에 insert 하기 위해 service 클래스의 멤버메서드를 호출합니다.
        int result = ms.insertRecord(id, amount, error);

        String page = "buy_ticket_success";
        if(result != 0){
            page = "buy_ticket_fail";
        }

        model.addAttribute("id", id);
        model.addAttribute("amount", amount);
        model.addAttribute("error", error);

        return page;


    }
}
