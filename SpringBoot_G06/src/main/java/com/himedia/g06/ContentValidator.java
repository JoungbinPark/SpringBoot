package com.himedia.g06;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }   // 얘는 거의 사용할 일이 없습니다.


    @Override
    public void validate(Object target, Errors errors) {
        // Object target : 검사할 객체(contentdto)를 받는 매개변수(Object형). 전달된 객체멤버변수가 비어있는지 검사 예정
        // Errors errors : 보내온 객체에 에러내용을 담을 매개변수

        //자료형 복원
        ContentDto dto = (ContentDto) target;

        /* 검사방법 1
        //전송된 값들 추출
        String sWriter = dto.getWriter();
        String sContent = dto.getContent();
        //각 필드가 null이거나 비어있다면 field에 멤버변수이름을, errorCode에 내용을 넣습니다.
        if(sWriter == null || sWriter.isEmpty()){
            errors.rejectValue("writer", "trouble");
        }
        if(sContent == null || sContent.trim().isEmpty()){
            errors.rejectValue("content", "trouble");
        }
        */

        //검사방법 2
        //전달객체의 멤버변수를 꺼내보지 않고 널이거나 비어있는지 점검. 결과는 errors에 저장
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }   //얘도 거의 사용할 일이 없습니다.
}
