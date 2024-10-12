package gangnam.study.week4.assignment.users.domain.dto;


import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;  // 게시글 ID
    private String title;  // 게시글 제목
    private String content;  // 게시글 내용
    private String username;  // 게시글 작성자
}