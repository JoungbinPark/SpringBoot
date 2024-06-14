package com.himedia.g02;
// 현재 클래스가 클라이언트의 주소에서 요청을 받아서 기능별로 jsp까지 연결해주는 클래스
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


// 현재 클래스가 Controller 클래스임을 어너테이션으로 표시
@Controller
public class SpringBootController {

    // 이곳에 생성되는 메서드들이 기존 각 클래스들의 execute 메서드 역할을 할 것입니다.
    // 각 요청은 역시 메서드에 붙은 어너테이션으로 구분합니다.
    // 아래 메서드(first)는 http://localhost:8070/ 라는 요청이 있으면 실행됩니다.
    //@RequestMapping(value="/", method= RequestMethod.GET)
    @GetMapping("/")
    public String first(){
        return "index";
        // 파일 이름과 경로 전체를 써서 포워딩이나 sendRedirect하지 않고
        // String 형식으로 파일이름만 리턴해서 /WEB-INF/views/과 .jsp 들이 조립되고 이동합니다.
    }

    @GetMapping("/sub")
    public String sub(){
        return "sub";
    }

    @GetMapping("/direct")
    public @ResponseBody String direct(){
        return "<h1>JSP in Gradle~!!</h1>";
    }
}
