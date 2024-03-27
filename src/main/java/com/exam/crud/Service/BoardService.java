package com.exam.crud.Service;

import com.exam.crud.DTO.BoardDTO;
import com.exam.crud.Entity.BoardEntity;
import com.exam.crud.Repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//순서 보장을 위한 클래스
@Service
//repository, entity, dto를 주입처리
@RequiredArgsConstructor
//데이터베이스 처리를 모아서 처리(일괄처리)
@Transactional
public class BoardService {
    //사용할 저장소
    private final BoardRepository boardRepository;
    //변환 처리
    private final ModelMapper modelMapper;

    //view에서 DTO전달->Entity변환 후 데이터베이스에 저장
    public void update(BoardDTO boardDTO ,Model model){
        //수정할 데이터가 존재하는가?
        //수정할 id로 조회
        Optional<BoardEntity> temp =boardRepository.findById(boardDTO.getBno());//조회
        if(temp.isPresent()) {//존재하면
            //DTO의 내용을 Entity의 내용에 맞게 매칭처리
            BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
            //사용할 데이터베이스 질의어로 데이터베이스 구동
            //저장확인 가능
            //데이터베이스 처리르 위한 변환 DTO_>Entity ((BoardDTO boardDTO)
            boardRepository.save(boardEntity);//질의어
            //결과를 Controller에 전달하기 위해 Entity->DTO(BoardDTO boardDTO)
        }
    }
    //삭제
    public void delete(Long bno){
    boardRepository.deleteById(bno); //해당 번호 자료 삭제
    }
    //개별조회
    //요청번호->조회해서->DTO변환 후 ->View전달
    public BoardDTO read(Long bno){
        //요청한 번호를 조회
        System.out.println(bno);
        Optional<BoardEntity>temp=boardRepository.findById(bno);
        System.out.println(temp.toString());
        //변환
//        BoardDTO boardDTO=modelMapper.map(temp,BoardDTO.class);
//        return  boardDTO;
        return modelMapper.map(temp,BoardDTO.class);
    }
    //전체조회 List, Pageable
    //pageable == 값, 페이지정보
    //public Page(전달시 페이지 정보) List(pageable 작업할 페이지 번호)
    public Page<BoardDTO> list(Pageable page, String type, String search){

        //페이지정보를 읽기 위한 정렬
        int currentPage = page.getPageNumber()-1; //데이터베이스 페이지번호 변경
        int pageLimit= 10; //한 화면에 출력할 페이지 갯수

        //페이지 처리를 위한 정렬 작업
        //PageRequest.of 페이지 요청(찾을 페이지, 가져올 갯수, 정렬(정렬형식 Desc(내림차순), ASC(오름차순))
        Pageable pageable = PageRequest.of(currentPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"bno"));

        Page<BoardEntity> boardEntities;

        //검색을 추가
        if(type.equals("t") && search !=null){//제목 선택, 검색어가 있으면
            //JPA 기본기능으로 조회처리 X, 사용자가 SQL을 작성
            boardEntities = boardRepository.SubjectSearch(search, pageable);
        }else if (type.equals("c") && search !=null){//내용을 선택, 검색어가 있으면
            boardEntities = boardRepository.ContentSearch(search, pageable);
        }else if (type.equals("w") && search !=null){
            boardEntities = boardRepository.WriterSearch(search, pageable);
        }else if (type.equals("tcw") && search !=null){
            boardEntities =  boardRepository.AllSearch(search, pageable);
        }else{
            //전체조회
            boardEntities=boardRepository.findAll(pageable);
        }

        Page<BoardDTO> boardDTOS = boardEntities.map(data->modelMapper.map(data,BoardDTO.class));

        return boardDTOS;
    }





    public void insert(BoardDTO boardDTO) {
        //데이터베이스 처리용
        //DTO의 내용을 Entity의 내용에 맞게 매칭처리
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        //사용할 데이터베이스 질의어로 데이터베이스 구동
        //저장확인 가능
        //데이터베이스 처리를 위한 변환 DTO -> Entity ((BoardDTO boardDTO))
        boardRepository.save(boardEntity);
        //결과를 Controller에 전달하기 위해 Entity -> DTO(public BoardDTO)
    }
}
