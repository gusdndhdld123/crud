package com.exam.crud.Service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class PageService{

    public static Map<String, Integer> Pagination(Page<?> page) {
        int currentPage = page.getNumber() + 1; //현재 페이지 번호 5
        int totalPage = page.getTotalPages(); //전체 페이지 수 23
        int blockLimit = 10; //화면에 출력할 페이지 개수

        Map<String, Integer> pageinfo = new HashMap<>(); //<키의 데이터형, 값의 데이터형>
        int startPage = Math.max(1, currentPage-blockLimit/2); //1, 중간값 중 큰 것을 선택해서 사용하겠다
        int endPage = Math.min(currentPage+blockLimit-1, totalPage);
        int prevPage = Math.max(1, currentPage-1);
        int nextPage = Math.min(currentPage+1, totalPage);
        int lastPage = totalPage;

        //controller에 값을 전달
        pageinfo.put("startPage", startPage);
        pageinfo.put("endPage", endPage);
        pageinfo.put("prevPage", prevPage);
        pageinfo.put("nextPage", nextPage);
        pageinfo.put("lastPage", lastPage);
        pageinfo.put("currentPage", currentPage);


        return pageinfo;

    }
}
//application에서 선언된 bean(변수)을 읽을 때 인식이 안되는 문제
//Bean을 이용할 때는 Service에 작성해서 이용
