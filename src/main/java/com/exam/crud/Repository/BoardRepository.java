package com.exam.crud.Repository;

import com.exam.crud.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//저장소를 위한 클래스
@Repository
//JpaRepository(사용할저장소(테이블))<사용할Entity,기본키 데이터형>
public interface BoardRepository
        extends JpaRepository<BoardEntity,Long> {
    //내용을 사용자가 원하는데로 구성(삽입,수정,삭제, 조회..기본제공)
    //.save()-삽입(기본키X),수정(기본키0)
    //DeleteBy(대상)변수명(첫글자대문자)   ------>Jpa 규칙
    //deleteById()해당아이디를 찾아서 삭제
    //FindAll()모두 검색
    //FindBy변수명-해당변수의 값에 해당하는 내용을 검색
    //FindById(번호)-해당번호를 찾아서 검색
    //FindBySubject(내용)-제목해서 찾아서 검색
    //FindbyIdAndSubject(번호,내용)-번호와 내용이 일치하는 내용을 찾아서 검색
    //FindByIdOrSubject(번호, 내용)-번호나 내용이 일치하는 내용을 찾아서 검색
    //JPA에 없는 내용은 사용자가 메소드로 만들어서 사용
}//데이터저장소
