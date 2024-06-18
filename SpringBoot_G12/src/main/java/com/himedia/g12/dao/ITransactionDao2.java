package com.himedia.g12.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITransactionDao2 {
    void insertRecord(String id, int amount);
}
