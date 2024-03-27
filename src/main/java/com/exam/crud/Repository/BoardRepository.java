package com.exam.crud.Repository;

import com.exam.crud.Entity.BoardEntity;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//저장소를 위한 클래스
@Repository
//JpaRepository(사용할저장소(테이블))<사용할Entity,기본키 데이터형>
public interface BoardRepository
        extends JpaRepository<BoardEntity,Long> {

    //사용자 질의어
    //@Query를 이용해서 데이터베이스 질의어를 작성. 방법은 두 가지
    //1. 엔티티에 변수명으로 질의어 작성
    //2. 테이블에 필드명으로 질의어 작성(네이티브)
    //@Query(Select 별칭 from 엔티티 별칭 where 별칭.변수 조건식 :값)
    //%내용 - 뒤의 내용이 일치하면, 내용% - 앞에서 일치하면 %내용% - 포함되면
    //서비스에서 서치로 보낸 값을 서치에 저장한다
    @Query("SELECT b from BoardEntity b where b.title like %:search%")
    Page<BoardEntity> SubjectSearch(@Param("search")String search, Pageable pageable);

    @Query("select b from BoardEntity b where b.content like %:search%")
    Page<BoardEntity> ContentSearch(String search, Pageable pageable);

    @Query("select b from BoardEntity b where b.writer like %:search%")
    Page<BoardEntity> WriterSearch(String search, Pageable pageable);

    @Query("select b from BoardEntity b where b.title %:search% or b.content like %:search% or b.writer like %:search%")
    Page<BoardEntity> AllSearch(String search, Pageable pageable);










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
