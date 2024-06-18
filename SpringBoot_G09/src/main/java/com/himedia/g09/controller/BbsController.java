package com.himedia.g09.controller;

import com.himedia.g09.dao.BbsDao;
import com.himedia.g09.dto.BbsDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BbsController {

    @Autowired
    BbsDao bdao;

    @GetMapping("/")
    public ModelAndView root(){
        ModelAndView mav = new ModelAndView();

        List<BbsDto> list = bdao.select();
        mav.addObject("list", list);
        mav.setViewName("bbslist");
        return mav;
    }

    @GetMapping("/writeForm")
    public String writeForm(){

        return "writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("dto") @Valid BbsDto bbsdto, BindingResult result, Model model){

        String page = "redirect:/";
        if(result.hasErrors()){
            page = "writeForm";
            if(result.getFieldError("writer")!=null){
                model.addAttribute("message", result.getFieldError("writer").getDefaultMessage());
            } else if(result.getFieldError("title")!=null){
                model.addAttribute("message", result.getFieldError("title").getDefaultMessage());

            } else if(result.getFieldError("content")!=null){
                model.addAttribute("message", result.getFieldError("content").getDefaultMessage());
            }
        } else{
            bdao.insert(bbsdto);
        }
        //레코드 insert 후 게시물을 다시 조회해서 bbslist.jsp로 이동합니다.
        return page;
    }

    @GetMapping("/view")
    public ModelAndView view(@RequestParam("id") int id){
        ModelAndView mav = new ModelAndView();
        BbsDto bdto = bdao.getBbs(id);
        mav.addObject("dto", bdto);
        mav.setViewName("view");
        return mav;
    }

    @GetMapping("/updateForm")
    public ModelAndView updateForm(@RequestParam("id") int id){
        ModelAndView mav = new ModelAndView();
        BbsDto bdto = bdao.getBbs(id);
        mav.addObject("dto", bdto);
        mav.setViewName("updateForm");
        return mav;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("dto") @Valid BbsDto bbsdto, BindingResult result, Model model) {

        String page = "redirect:/view?id=" + bbsdto.getId();
        if (result.hasErrors()) {
            page = "updateForm";
            if (result.getFieldError("writer") != null) {
                model.addAttribute("message", result.getFieldError("writer").getDefaultMessage());
            } else if (result.getFieldError("title") != null) {
                model.addAttribute("message", result.getFieldError("title").getDefaultMessage());
            } else if (result.getFieldError("content") != null) {
                model.addAttribute("message", result.getFieldError("content").getDefaultMessage());
            }
        } else {
            bdao.update(bbsdto);
        }
        //레코드 insert 후 게시물을 다시 조회해서 bbslist.jsp로 이동합니다.
        return page;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        //전달받은 id로 게시물 삭제 후 리스트로 이동
        bdao.delete(id);
        return "redirect:/";
    }
}