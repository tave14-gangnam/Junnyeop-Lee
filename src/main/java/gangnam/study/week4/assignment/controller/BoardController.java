package gangnam.study.week4.assignment.controller;

import gangnam.study.week4.assignment.users.domain.dto.BoardRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.BoardResponseDto;
import gangnam.study.week4.assignment.users.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 생성
     *
     * [POST 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/boards?userId={userId}
     * Method: POST
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "title": "게시글 제목",
     *   "content": "게시글 내용",
     *   "department": "DEVELOPMENT"
     * }
     * Path Parameter: userId (게시글 작성자 ID)
     */
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody  BoardRequestDto requestDto, @RequestParam Long userId) {
        BoardResponseDto boardResponseDto = boardService.createBoard(requestDto, userId);
        return ResponseEntity.ok(boardResponseDto);
    }

    /**
     * 게시글 조회 (ID로 조회)
     *
     * [GET 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/boards/{id}
     * Method: GET
     * Path Variable: id (게시글 ID)
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.getBoardById(id);
        return ResponseEntity.ok(boardResponseDto);
    }

    /**
     * 게시글 수정 (ID로 수정)
     *
     * [PUT 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/boards/{id}
     * Method: PUT
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "title": "수정된 게시글 제목",
     *   "content": "수정된 게시글 내용"
     * }
     * Path Variable: id (게시글 ID)
     */
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody  BoardRequestDto requestDto) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto);
        return ResponseEntity.ok(boardResponseDto);
    }

    /**
     * 게시글 삭제 (ID로 삭제)
     *
     * [DELETE 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/boards/{id}
     * Method: DELETE
     * Path Variable: id (게시글 ID)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
