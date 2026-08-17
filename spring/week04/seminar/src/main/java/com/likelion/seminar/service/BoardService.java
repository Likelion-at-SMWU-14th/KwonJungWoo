package com.likelion.seminar.service;

import com.likelion.seminar.dto.BoardDTO;
import com.likelion.seminar.entity.Board;
import com.likelion.seminar.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /** Board 생성 **/
    @Transactional
    public void createBoard(BoardDTO boardDTO) {
        Board board = new Board(boardDTO.getName());

        boardRepository.save(board);
    }

    /** 개별 Board 조회 **/
    public BoardDTO getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."));

        return new BoardDTO(
                id,
                board.getName()
        );
    }

    /** 전체 Board 조회 **/
    public List<BoardDTO> getBoards() {
        List<Board> boards = boardRepository.findAll();

        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boards) {
            boardDTOList.add(
                    new BoardDTO(
                            board.getId(),
                            board.getName()
                    )
            );
        }

        return boardDTOList;
    }

    /** Board 수정 **/
    @Transactional
    public void updateBoard(Long id, BoardDTO boardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."));

        board.setName(boardDTO.getName());

        // @Transactional이 있기 때문에 save()를 호출하지 않아도 된다 !!
        // boardRepository.save(board);
    }

    /** Board 삭제 **/
    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."));

        boardRepository.delete(board);
    }
}
