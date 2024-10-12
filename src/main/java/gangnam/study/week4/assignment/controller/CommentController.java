package gangnam.study.week4.assignment.controller;

import gangnam.study.week4.assignment.users.domain.dto.CommentRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.CommentResponseDto;
import gangnam.study.week4.assignment.users.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     *
     * [POST 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/comments?userId={userId}&boardId={boardId}
     * Method: POST
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "content": "댓글 내용"
     * }
     * Path Parameters: userId (댓글 작성자 ID), boardId (게시판 ID)
     */
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @RequestBody  CommentRequestDto requestDto,
            @RequestParam Long userId,
            @RequestParam Long boardId) {
        CommentResponseDto commentResponseDto = commentService.createComment(requestDto, userId, boardId);
        return ResponseEntity.ok(commentResponseDto);
    }

    /**
     * 댓글 조회 (ID로 조회)
     *
     * [GET 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/comments/{id}
     * Method: GET
     * Path Variable: id (댓글 ID)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.getCommentById(id);
        return ResponseEntity.ok(commentResponseDto);
    }

    /**
     * 댓글 수정 (ID로 수정)
     *
     * [PUT 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/comments/{id}
     * Method: PUT
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "content": "수정된 댓글 내용"
     * }
     * Path Variable: id (댓글 ID)
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody  CommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(id, requestDto);
        return ResponseEntity.ok(commentResponseDto);
    }

    /**
     * 댓글 삭제 (ID로 삭제)
     *
     * [DELETE 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/comments/{id}
     * Method: DELETE
     * Path Variable: id (댓글 ID)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}