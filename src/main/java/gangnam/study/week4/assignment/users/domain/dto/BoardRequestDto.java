package gangnam.study.week4.assignment.users.domain.dto;


import gangnam.study.week4.assignment.users.domain.entity.Department;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private String title;  // 게시글 제목
    private String content;  // 게시글 내용
    private Department department;  // 부서 Enum (예: DEVELOPMENT, HR, DESIGN)
}
