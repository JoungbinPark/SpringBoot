package com.himedia.g06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ValidController {

    @GetMapping("/")
    public String main(){
        return "startPage";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("dto") ContentDto contentdto, BindingResult result, Model model){

        //전송된 파라미터를 검사해서 하나라도 비어있으면 startPage.jsp로 되돌아가고 정상 전송되었으면 result.jsp로 이동합니다.
        //validation 기능이 있는 클래스를 생성하고 그 객체를 이용해서 검사
        //클래스명은 ContentValidator, java의 validator 인터페이스를 implements 한 클래스
        ContentValidator valid = new ContentValidator();
        valid.validate( contentdto, result);
        // BindingResult result : 에러제목(key)과 내용(value)을 담을 수 있는 객체
        //validator의 멤버메서드인 validate가 contentdto 내용을 검사한 후 result에 오류내용을 담아주고, 리턴되지 않아도 call by reference이기 때문에 validate 메서드에서 넣어준 오류내용을 현재위치에서도 result라는 이름으로 확인 가능

        //model.addAttribute("dto", contentdto);
        if(result.hasErrors()){
            //model.addAttribute("message", "writer와 content는 비어있으면 안됩니다.");
            if(result.getFieldError("writer") != null){
                model.addAttribute("message", "writer가 비어있습니다.");
            } else if( result.getFieldError("content") != null){
                model.addAttribute("message", "content가 비어있습니다.");
            }
            return "startPage";
        }else{
            return "result";
        }
    }
}
