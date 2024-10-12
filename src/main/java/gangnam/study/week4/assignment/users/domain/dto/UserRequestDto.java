package gangnam.study.week4.assignment.users.domain.dto;

import gangnam.study.week4.assignment.users.domain.entity.Department;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {


    private String username;


    private String password;


    private String email;

    private Department department;  // Department Enum 사용
}
