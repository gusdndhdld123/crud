Controller 검증
Form있는 페이지만 검증이 가능

1.입력폼에 반드시 DTO를 전달
2. 입력처리에서 검증처리
3. 수정폼은 수정할 DTO전달(작성X)
4. 수정처리에서 검증처리

전달하는 값의 변수는 주고받는 변수명이 동일하게
public String text(BoardDTO boardDTO, Model model){
                model.addattribute("boardDTO")검증가능
                model.addattribute("data"...)검증불가
}
기존 입력폼
@getMapping("/board/insert")
public String text(Model model){
return "board/insert"
}
검증 입력폼
@getMapping("/board/insert")
public String text(Model model){
model.addAttribute("boardDTO", new BoardDTO())
return "board/insert"
}