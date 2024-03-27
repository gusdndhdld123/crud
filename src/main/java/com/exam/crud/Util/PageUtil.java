package com.exam.crud.Util;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

//View 하단에 페이지 버튼을 제어할 값들을 계산처리
//변수는 값만 저장, 배열은 값들을 저장 =>변수명 1개
//map은 키(변수명), 값을 저장 =>변수명 여러개
//데이터베이스는 0부터 시작

public class PageUtil {

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
        pageinfo.put("totalPage", totalPage);
        pageinfo.put("blockLimit", blockLimit);

        return pageinfo;

    }
}
//application에서 선언된 bean(변수)을 읽을 때 인식이 안되는 문제
//Bean을 이용할 때는 Service에 작성해서 이용
