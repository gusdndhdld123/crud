package com.exam.crud.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

//Entity 그냥 복사떠옴 클래스이름만 바꿈
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
//Entity(1)<->DTO(다)
//하나의 Entity를 사용용도별로 DTO를 나누어서 작업
//검증처리(View에서 입력내용이 정상적인 값인지 판별)
public class BoardDTO{

    private Long bno;
    //게시판 제목, 내용, 작성자, 생성일, 수정일
    //@검증종류(message ="오류메세지")
    @NotBlank(message = "제목은 생략하실 수 없습니다")
    private String title;
    @NotBlank(message = "내용은 생략하실 수 없습니다")
    private String content;
    @NotBlank(message = "작성자는 생략하실 수 없습니다")
    private String writer;
    private LocalDateTime modDate;
    //Entity에 없는 변수 선언 가능(주문관리->금액처리)

//    private Integer sum;

    //변수를 처리할 필요한 메소드를 작성
    //Entity<->DTO간의 변환하는 메소드
    //생성작업

}//비즈니스 모델
//데이터 교환

