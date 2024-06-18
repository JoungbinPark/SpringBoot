package com.himedia.g08.controller;

import com.himedia.g08.dao.UserDao;
import com.himedia.g08.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserDao udao;


    @GetMapping("/")
    public String index(Model model){
        //UserDao udao = new UserDao();
        List<UserDto> list = udao.selectAll();
        model.addAttribute("list", list);

        return "userlist";
    }
}
