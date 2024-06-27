package com.himedia.g14.service;

import com.himedia.g14.dao.IAdminDao;
import com.himedia.g14.dto.AdminVO;
import com.himedia.g14.dto.Paging;
import com.himedia.g14.dto.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    IAdminDao adao;


    public AdminVO getAdmin(String adminid) {
        return adao.getAdmin(adminid);
    }

    public HashMap<String, Object> getAdminProductList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int page=1;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page", page);
        } else if ( session.getAttribute("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        } else {
            session.removeAttribute("page");
        }

        String key="";
        if(request.getParameter("key") != null) {
            key=request.getParameter("key");
            session.setAttribute("key", key);
        } else if( session.getAttribute("key") != null){
            key=(String)session.getAttribute("key");
        } else {
            session.removeAttribute("key");
        }

        Paging paging = new Paging();
        paging.setPage(page);
        int count = adao.getAllCount("product", "name", key);
        paging.setTotalCount(count);
        paging.calPaging();
        paging.setStartNum(paging.getStartNum()-1);

        List<ProductVO> list = adao.getProductList(paging, key);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("productList", list);
        result.put("paging", paging);
        result.put("key", key);

        return result;


    }

    public void insertProduct(ProductVO productvo) {
        adao.insertProduct(productvo);
    }
}
