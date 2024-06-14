package com.himedia.g05;

import com.himedia.g05.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestController {

    @Test
    public void testmethod(){

        //@AllArgsConstructor 사용
        MemberDto mdto1 = new MemberDto("scott", "김하나");

        //@Builder 사용
        MemberDto mdto = MemberDto.builder()
                .id("hong")
                .build();
        System.out.println(mdto.getId());
        System.out.println(mdto.getName());
        System.out.println(mdto);
    }


}
