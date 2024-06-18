package com.himedia.g10.controller;

import com.himedia.g10.dao.IUserDao;
import com.himedia.g10.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserDao iudao;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();



        List<UserDto> list = iudao.select();
        mav.addObject("list", list);

        mav.setViewName("userlist");
        return mav;


    }
}
