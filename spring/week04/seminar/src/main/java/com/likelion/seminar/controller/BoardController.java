package com.likelion.seminar.controller;

import com.likelion.seminar.dto.BoardDTO;
import com.likelion.seminar.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@RequestBody BoardDTO boardDTO) {
        boardService.createBoard(boardDTO);
    }

    @GetMapping("/{id}")
    public BoardDTO getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @GetMapping
    public List<BoardDTO> getBoards() {
        return boardService.getBoards();
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id,
                            @RequestBody BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
