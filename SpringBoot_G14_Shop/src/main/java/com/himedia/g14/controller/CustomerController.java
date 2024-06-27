package com.himedia.g14.controller;

import com.himedia.g14.dto.MemberVO;
import com.himedia.g14.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    @Autowired
    CustomerService cs;

    @GetMapping("/customer")
    public String customer(){
        return "/customer/intro";
    }

    @GetMapping("/qnaList")
    public ModelAndView qnaList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("qnaList", cs.getQnaList());
        mav.setViewName("customer/qnaList");
        return mav;
    }

    @GetMapping("/qnaView")
    public ModelAndView qnaView(@RequestParam("qseq") int qseq){
        ModelAndView mav = new ModelAndView();
        mav.addObject("qnaVO", cs.getQna(qseq));
        mav.setViewName("customer/qnaView");
        return mav;
    }

    @GetMapping("/qnaWriteForm")
    public String qnaWriteForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        String url = "customer/qnaWriteForm";
        if(loginUser == null){
            return "member/login";
        }else {
            return "customer/qnaWriteForm";
        }
    }


    @PostMapping("writeQna")
    public String writeQna(
            HttpServletRequest request,
            @RequestParam(value="secret", required=false, defaultValue="M") String secret,
            @RequestParam(value="pass", required=false, defaultValue="") String pass,
            @RequestParam("subject") String subject,
            @RequestParam("content") String content,
            Model model){
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        String url = "customer/qnaWriteForm";
        if(loginUser == null){
            return "member/login";
        }else {
            if(subject == null || subject.equals("")){
                model.addAttribute("message", "제목을 입력하세요");
            } else if(content == null || content.equals("")){
                model.addAttribute("message","내용을 입력하세요");
            } else{
                url="redirect:/qnaList";
                cs.writeQna(loginUser.getUserid(), subject, content, secret, pass);
            }
        }
        return url;
    }
}
