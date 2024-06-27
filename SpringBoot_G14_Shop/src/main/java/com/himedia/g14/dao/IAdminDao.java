package com.himedia.g14.dao;

import com.himedia.g14.dto.AdminVO;
import com.himedia.g14.dto.Paging;
import com.himedia.g14.dto.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAdminDao {
    AdminVO getAdmin(String adminid);

    int getAllCount(String tablename, String fieldname, String key);

    List<ProductVO> getProductList(Paging paging, String key);

    void insertProduct(ProductVO productvo);
}
