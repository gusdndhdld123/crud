package com.exam.crud.Entity;

import jakarta.persistence.*;
import lombok.*;

//데이터베이스 테이블
//가능하면 소문자로 구성, _문자로 단어 분리X         JPA에서 _다 오류걸림
//기본키 id(유사하게)
//나머지 필드는 항목이름으로 영문
//테이블명 작업이름
//TOString은 모두변수를 전달
//위의 어노테이션6개는 모를땐 만들고 써도 된다.~
@Getter @Setter @ToString
//클래스 생성자 BoardEntity(), BoardEntity(변수,......)
@AllArgsConstructor @NoArgsConstructor
//변수의 값을 getter와 setter를 이용하지 않고 변경
@Builder
//데이터베이스 테이블을 위한 클랙스
@Entity
//자바17 jakarta, 자바15이하 javax
@Table(name="board")
//테이블 카운트에 정보를 저장할 테이블
//initialValue=시작값, allocationSize=증가값
@SequenceGenerator(
        name="board_seq",
        sequenceName="board_seq",
        initialValue=1,
        allocationSize=1
)
public class BoardEntity extends BaseEntity{
    //기본키,유일한 키-중복X
    //기본키로 사용할 변수
    @Id
    //생성전략
    //GenerationType.IDENTITY-중복되지 않도록 처리
    //GenerationType.SEQUENCE -숫자를 증가하면서 처리
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "board_seq")
    private Long bno;
    //게시판 제목, 내용, 작성자, 생성일, 수정일
    private String title;
    private String content;
    private String writer;
    //변수를 처리할 필요한 메소드를 작성
    //Entity<->DTO간의 변환하는 메소드
    //생성작업

}
