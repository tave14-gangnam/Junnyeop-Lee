package gangnam.study.week4.assignment.users.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;  // 댓글 ID
    private String content;  // 댓글 내용
    private String username;  // 댓글 작성자
    private String boardTitle;  // 게시판 제목
}
