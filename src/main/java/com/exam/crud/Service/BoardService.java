package com.exam.crud.Service;

import com.exam.crud.DTO.BoardDTO;
import com.exam.crud.Entity.BoardEntity;
import com.exam.crud.Repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public void update(BoardDTO boardDTO){
        //수정할 데이터가 존재하는가?
        //수정할 id로 조회
        Optional<BoardEntity>temp=boardRepository.findById(boardDTO.getId());//조회
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
    public void delete(Long id){
    boardRepository.deleteById(id); //해당 번호 자료 삭제
    }
    //개별조회
    //요청번호->조회해서->DTO변환 후 ->View전달
    public BoardDTO read(Long id){
        //요청한 번호를 조회
        Optional<BoardEntity>temp=boardRepository.findById(id);
        //변환
//        BoardDTO boardDTO=modelMapper.map(temp,BoardDTO.class);
//        return  boardDTO;
        return modelMapper.map(temp,BoardDTO.class);
    }
    //전체조회 List, Pageable
    public List<BoardDTO> list(){
        //전체조회
        List<BoardEntity>boardEntities=boardRepository.findAll();
        //변환
        //Arrays.asList :List의 내용을 개별로 읽어서 변환 후 배열로 저장
        List<BoardDTO>boardDTOS= Arrays.asList(modelMapper.map(boardEntities, BoardDTO[].class));
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
