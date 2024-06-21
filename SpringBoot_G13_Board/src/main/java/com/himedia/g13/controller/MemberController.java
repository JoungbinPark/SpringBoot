package com.himedia.g13.controller;

import com.google.gson.Gson;
import com.himedia.g13.dto.KakaoProfile;
import com.himedia.g13.dto.MemberDto;
import com.himedia.g13.dto.OAuthToken;
import com.himedia.g13.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MemberController {

    @Autowired
    MemberService ms;

    @GetMapping("/")
    public String root(){
        return "member/loginForm";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("dto") @Valid MemberDto memberdto, BindingResult result, Model model, HttpServletRequest request){
        String url = "member/loginForm";
        if(result.getFieldError("userid")!=null){
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        } else if(result.getFieldError("pwd")!=null){
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        } else{
            //정상 로그인 절차 진행
            MemberDto mdto = ms.getMember(memberdto.getUserid());
            if( mdto == null) {
                model.addAttribute("message", "아이디/패스워드를 확인하세요.");
            } else if( !mdto.getPwd().equals(memberdto.getPwd())) {
                model.addAttribute("message", "아이디/패스워드를 확인하세요.");
            } else if(mdto.getPwd().equals(memberdto.getPwd() )){
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", mdto);
                url = "redirect:/boardlist";

            } else {
                model.addAttribute("message", "관리자에게 문의하세요");
            }
        }

        return url;
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "member/joinForm";
    }

    @GetMapping("/idcheck")
    public String idcheck(@RequestParam("userid") String userid, Model model){
        MemberDto mdto = ms.getMember( userid );
        if(mdto == null) model.addAttribute("result", -1);
        else model.addAttribute("result", 1);
        model.addAttribute("userid", userid);
        return "member/idcheck";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("dto") @Valid MemberDto mdto,
                       BindingResult result,
                       @RequestParam(value="reid", required=false) String reid,
                       @RequestParam(value="pwd_check", required=false) String pwd_check,
                       Model model){
        String url = "member/joinForm";
        if(result.getFieldError("userid")!=null)
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        else if(result.getFieldError("pwd")!=null)
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        else if(result.getFieldError("name")!=null)
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
        else if(result.getFieldError("phone")!=null)
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
        else if(result.getFieldError("email")!=null)
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
        else if( reid==null || !mdto.getUserid().equals(reid))
            model.addAttribute("message", "아이디 중복체크가 되지 않았습니다.");
        else if( pwd_check==null || !mdto.getPwd().equals(pwd_check))
            model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다.");
        else {
            ms.insertMember(mdto);
            model.addAttribute("message", "회원가입이 완료되었습니다. 로그인하세요.");
            url="member/loginForm";
        }
        return url;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/updateMemberForm")
    public String updateMemberForm(){
        return "member/updateMemberForm";
    }

    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute("dto") @Valid MemberDto mdto,
                               BindingResult result,
                               @RequestParam(value="pwd_check", required=false) String pwd_check,
                               Model model,
                               HttpServletRequest request){
        String url = "member/updateMemberFrom";
        if(result.getFieldError("pwd")!=null)
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        else if(result.getFieldError("name")!=null)
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
        else if(result.getFieldError("phone")!=null)
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
        else if(result.getFieldError("email")!=null)
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
        else if( pwd_check==null || !mdto.getPwd().equals(pwd_check))
            model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다.");
        else {
            ms.updateMember(mdto);
            HttpSession session = request.getSession();
            url="redirect:/boardlist";
        }
        return url;
    }

    @GetMapping("/deleteMember")
    public String deleteMember(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        MemberDto mdto = (MemberDto)session.getAttribute("loginUser");
        ms.deleteMember(mdto.getUserid());
        model.addAttribute("message", "회원탈퇴가 완료되었습니다.");
        return "member/loginForm";
    }

    @GetMapping("/kakaostart")
    public @ResponseBody String kakaostart(){
        String a = "<script type='text/javascript'>"
                + "location.href='https://kauth.kakao.com/oauth/authorize?"
                + "client_id=4c345339fb66e95e889168ad69802a7b"
                + "&redirect_uri=http://localhost:8060/kakaoLogin"
                + "&response_type=code'"
                + "</script>";
        return a;
    }

    @GetMapping("/kakaoLogin")
    public String login(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        System.out.println(code); //토큰출력

        //실제 User info를 요청할 url과 전달인수 설정
        String endpoint="https://kauth.kakao.com/oauth/token";
        URL url = new URL(endpoint);    //import java.net.URL;
        String bodyData="grant_type=authorization_code";
        bodyData += "&client_id=4c345339fb66e95e889168ad69802a7b";
        bodyData += "&redirect_uri=http://localhost:8060/kakaoLogin";
        bodyData += "&code="+code;

        //Stream 연결
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        //http header에 값 넣기
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        //인증절차를 완료하고 User info 요청을 위한 정보를 요청 및 수신합니다.
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(conn.getOutputStream(), "UTF-8")
        );
        bw.write(bodyData);
        bw.flush();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
        );

        String input="";
        StringBuilder sb = new StringBuilder(); //조각난 String을 조립하기 위한 객체
        while((input=br.readLine())!=null){
            sb.append(input);
            System.out.println(input);
        }

        // 사용자가 실제로 동의한 항목들만 요청하고 받을 수 있도록 권한을 갖고 있는 새로운 토큰으로 구성
        // {"access_token":"VjkVpjFvYN_UEh21FsLH6XCtsnNCjBWRAAAAAQo9dNkAAAGQLmdkaNEMsmlHt4Ko","token_type":"bearer","refresh_token":"_d-PbKCl_TbrzxuW-FMRh5faRg2k-DVfAAAAAgo9dNkAAAGQLmdkY9EMsmlHt4Ko","expires_in":21599,"scope":"profile_nickname","refresh_token_expires_in":5183999}

        // 수신내용을 GSon으로 변경(파싱)
        Gson gson= new Gson();
        OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);
        //oAuthToken <- sb{"access_token":"VjkVpjFvYN_UEh21FsLH6XCtsnNC....
        //sb 내용을 항목에 맞춰서 OAuthToken 객체에 옮겨 담습니다.

        //실제 정보 요청 및 수신
        String endpoint2="https://kapi.kakao.com/v2/user/me";
        URL url2 = new URL(endpoint2);
        HttpsURLConnection conn2=(HttpsURLConnection) url2.openConnection();
        //header 값 넣기
        conn2.setRequestProperty("Authorization", "Bearer "+oAuthToken.getAccess_token());
        conn2.setDoOutput(true);
        //UserInfo 수신
        BufferedReader br2 = new BufferedReader(
                new InputStreamReader(conn2.getInputStream(), "UTF-8")
        );
        String input2 = "";
        StringBuilder sb2 = new StringBuilder();
        while((input2=br2.readLine())!=null){
            sb2.append(input2);
            System.out.println(input2);
        }

        //{"id":3586438918,"connected_at":"2024-06-19T02:50:46Z","properties":{"nickname":"박정빈"},"kakao_account":{"profile_nickname_needs_agreement":false,"profile":{"nickname":"박정빈","is_default_nickname":false}}}

        Gson gson2 = new Gson();
        KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);

        System.out.println(kakaoProfile.getId());
        KakaoProfile.KakaoAccount ac = kakaoProfile.getAccount();
        System.out.println(ac.getEmail());
        KakaoProfile.KakaoAccount.Profile pf = ac.getProfile();
        System.out.println(pf.getNickname());

        MemberDto mdto = ms.getMember( kakaoProfile.getId() );
        if( mdto==null){
            mdto = new MemberDto();
            mdto.setUserid( kakaoProfile.getId() );
            //mdto.setEmail( ac.getEmail() );
            mdto.setEmail("kakao");
            mdto.setName( pf.getNickname());
            mdto.setProvider("kakao");
            mdto.setPwd("kakao");
            mdto.setPhone("");
            ms.insertMember(mdto);
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", mdto);
        return "redirect:/boardlist";
    }
}
