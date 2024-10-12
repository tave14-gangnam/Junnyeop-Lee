package gangnam.study.week4.assignment.users.domain.dto;


import gangnam.study.week4.assignment.users.domain.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String username;  // 유저 이름 (nullable, null일 경우 수정하지 않음)
    private String email;     // 유저 이메일 (nullable, null일 경우 수정하지 않음)
    private Department department;  // 유저 부서 (nullable, null일 경우 수정하지 않음)
}
