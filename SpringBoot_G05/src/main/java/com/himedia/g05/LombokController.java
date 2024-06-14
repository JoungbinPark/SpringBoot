package com.himedia.g05;

import com.himedia.g05.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LombokController {

    @GetMapping("/")
    public String root() {
        return "testForm";
    }

    @GetMapping("/test1")
    public String test1(
            @ModelAttribute("mdto") MemberDto memberdto,
            Model model){
        System.out.println("id : " + memberdto.getId());
        System.out.println("name : " + memberdto.getName());
        return "test1";
    }
}
