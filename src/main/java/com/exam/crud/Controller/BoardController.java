package com.exam.crud.Controller;

import com.exam.crud.DTO.BoardDTO;
import com.exam.crud.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

//주석 사용법
//TODO : 1. 검증
//TODO : 2. 페이지
//FIXME : 처리해야 할 것


//콘솔에 로그형식 내용을 출력
@Log4j2
@Controller
//controller>Service>repository
//제어를 위한 클래스
@RequiredArgsConstructor
public class BoardController {
    //서비스를 주입
    private final BoardService boardService;
    //  @Autowired는 private 클래스명으로 선언시 자동주입
    //@RequiredAresConstructor는 private final 클래스명으로 선언시 자동주입
    //form 태그가 있는 페이지는 get/post 존재
    //form 태그가 없는 페이지는 get/post 중 선택
    //삽입(C) ~Form(view로 이동), ~Proc(Service로 처리)
    //Service처리한 페이지 redirect로 이동
    //Model은 콘트롤러에서 뷰에 값을 전달
    @GetMapping("/board/insert")
    public String insertForm(Model model){
        log.info("입력폼으로 이동");
        log.warn("경고나 주의 메세지");
        log.error("문제발생 메세지");
        //"boardDTO"와 view ${boardDTO}가 같아야함
        model.addAttribute("boardDTO",new BoardDTO());
        return "board/insert"; //html파일로 이동

    }
    //입력한 내용을 DTO로 받는다
    //form(input태그 name)과 DTO(변수명) 매치 값을 전달
    //@ModelAttribute 매칭(생략가능)
    @PostMapping("/board/insert")
    //1. @Validated 대상 : 값이 들어오면 DTO를 이용하여 검증
    //2. BindingResult : DTO에서 검증한 결과를 전달
    public String insertProc(@Validated BoardDTO boardDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("서비스, post insert 검증 오류");
            return "board/insert";
        }
        //서비스처리
        boardService.insert(boardDTO);
        return "redirect:/board/list"; //이동할 맵핑명
    }
    //수정(U)-DTO에 변수명
//    http://localhost:8080/board/update?id=12
//    http://localhost:8080/board/update/12
    //Path(맵핑에)Variable(변수)로 전달받은 값을 Long id에 저장
    @GetMapping("/board/update/{bno}")
    //Model은 데이터베이스에서 받은 내용을 html에 전달할게 있으면
    public String updateForm(Long bno,Model model){//(받고, 보낼)
        //개별조회 후 수정폼에 출력(조회한 내용을 DTO에 저장)
        BoardDTO boardDTO=boardService.read(bno);
        //HTML에 값을 전달
        model.addAttribute("boardDTO",boardDTO);//"변수명",보낼값
        //서비스처리(조회)
        return "board/update";
    }
    //수정한 내용을 boardDTO가 받아서 데이터베이스에 수정처리
    //검증처리 추가
    @PostMapping("/board/update")
    public String updateProc(@Validated BoardDTO boardDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            log.error("서비스 인서트 포스트 오류");
            return "/board/update";
        }

        boardService.insert(boardDTO);
        //서비스처리(수정)
        return "redirect:/board/list";
    }
    //삭제
    @GetMapping("/board/delete/{bno}")
    public String deleteProc(@PathVariable Long bno, Model model){
        boardService.delete(bno);
        //서비스처리(삭제)
        return "redirect:/board/list";
    }
    @GetMapping("/board/list")
    public String listForm(Model model) {
        List<BoardDTO>boardDTOList= boardService.list();
        //서비스처리(전체조회)
        model.addAttribute("list",boardDTOList);
        return "/board/list";
    }
    @GetMapping("/board/{bno}")//대괄호나오면 @path
    public String readForm(@PathVariable Long bno,Model model){
        BoardDTO boardDTO=boardService.read(bno);
        //서비스에서 값을 받으면 반드시 model로 전달
        model.addAttribute("boardDTO",boardDTO);
        return "/board/read";
    }
//    Model(변수)Andview(페이지)
//    public ModelAndView readForm(){
//    }
//    public String(View) readForm(Model){
//
//    }
}//요청처리
//Model model은붙여도 오류가없다 모르면 붙여도된다 ㅋㅋ