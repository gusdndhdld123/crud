package com.exam.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//클래스는 사용방법 3가지 => 선언해서 사용
//extends - 현재 클래스에 다른 클래스를 추가해서 하나로 사용(상속)
//implements - 다른 클래스의 내용을 바꾸어서 사용(인터페이스)
//new - 다른 클래스 잠시 사용하고 반납

//어노테이션 개발자의 프로그램 개발시간 단축하는 기능
//선언-클래스나 메소드의 역할
//주입-클래스를 생성
//생성-메소드를 자동으로 생성

@SpringBootApplication
//자동으로 날짜를 생성하는 어노테이션
@EnableJpaAuditing
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

}
