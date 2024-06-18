package com.himedia.g12.service;

import com.himedia.g12.dao.ITransactionDao1;
import com.himedia.g12.dao.ITransactionDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class MyService {

    @Autowired
    ITransactionDao1 tdao1;

    @Autowired
    ITransactionDao2 tdao2;

    @Autowired
    PlatformTransactionManager ptm;

    @Autowired
    TransactionDefinition td;

    //트랜잭션 원자성 구현 #3
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int insertRecord(String id, int amount, int error) {
        int result = 0;
        tdao1.insertRecord(id, amount);
        if (error != 0) {
            result = 1;
            int n = 10 / 0;
        }
        tdao2.insertRecord(id, amount);


        return result;
    }

    /*
    // 트랜잭션 원자성 구현 #2
    @Autowired
    TransactionTemplate tt;

    public int insertRecord(String id, int amount, int error) {
        int result = 0;
        try {
            tt.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    tdao1.insertRecord(id, amount);
                    if (error != 0) {
                        int n = 10 / 0;
                    }
                    tdao2.insertRecord(id, amount);
                    System.out.println("Commit");
                }
            });
        } catch (Exception e) {
            result = 1;
            System.out.println("RollBack");
        }
        return result;
    }
    */


    /*
    // 트랜젝션 원자성 구현 #1
    public int insertRecord(String id, int amount, int error) {
        // transaction1 테이블과 transaction2 테이블에 각각 같은 내용으로 레코드를 insert하는 메서드를 호출
        int result = 0;

        //트랜젝션의 시작
        TransactionStatus status = ptm.getTransaction(td);
        //끝은 commit 또는 rollback

        try {
            tdao1.insertRecord(id, amount);
            if (error != 0) {
                int n = 10 / 0;
            }
            tdao2.insertRecord(id, amount);
            ptm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            result=1;
            ptm.rollback(status);
        }
        return result;
    }
     */

}

