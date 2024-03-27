package com.exam.crud.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//환경설정을 위한 클래스
@Configuration
public class AppConfig {
    //변수및 메소드를 환경설정에 등록
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    //기존에 ModelMapper를 사용하려면
    // ModelMapper modelMapper=new ModelMapper();
    //환경 설정시
    //ModelMapper modelMapper;
}
