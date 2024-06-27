package com.himedia.g14.service;

import com.himedia.g14.dao.ICustomerDao;
import com.himedia.g14.dto.QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ICustomerDao cdao;

    public List<QnaVO> getQnaList() {
        return cdao.getQnaList();
    }

    public QnaVO getQna(int qseq) {
        return cdao.getQna(qseq);
    }

    public void writeQna(String userid, String subject, String content, String secret, String pass) {
        cdao.writeQna(userid, subject, content, secret, pass);
    }
}
