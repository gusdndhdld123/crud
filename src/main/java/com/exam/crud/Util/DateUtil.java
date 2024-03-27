package com.exam.crud.Util;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Component

//날짜관련 클래스
//지정한 날짜와 현재 날짜를 비교해서 ~분전, ~시간 전, ~일 전, ~개월 전, ~년 전
public class DateUtil {

    //날짜(Date)형식(Format)수정(Modify)하는 메서드
    //modDate 변환하고 싶은 날짜 정보(수정일/등록일)
    public String formatModDate(LocalDateTime modDate){

        LocalDateTime now = LocalDateTime.now(); //현재 시간정보 읽기
        //수정일과 now간의 분차를 구해서 minutes에 저장(60분까지, 60분->1시간)
        long minutes = ChronoUnit.MINUTES.between(modDate, now);
        long hours = ChronoUnit.HOURS.between(modDate, now);
        long days = ChronoUnit.DAYS.between(modDate, now);
        long months = ChronoUnit.MONTHS.between(modDate, now);
        long years = ChronoUnit.YEARS.between(modDate, now);

        if(minutes<60){
            return minutes + "분 전";
        } else if(hours<60){
            return hours + "시간 전";
        } else if(days<31){
            return days + "일 전";
        } else if(months<12){
            return months + "개월 전";
        } else{
            return years + "년 전";
        }

    }
}
