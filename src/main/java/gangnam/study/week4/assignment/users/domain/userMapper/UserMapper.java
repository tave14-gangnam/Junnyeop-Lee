package gangnam.study.week4.assignment.users.domain.userMapper;

import gangnam.study.week4.assignment.users.domain.dto.UserRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.UserResponse;
import gangnam.study.week4.assignment.users.domain.dto.UserResponseDto;
import gangnam.study.week4.assignment.users.domain.entity.User;

public class UserMapper {

    // UserRequestDto -> User 변환 메서드 (회원가입 시)
    public static User toUserSignup(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .email(userRequestDto.getEmail())
                .department(userRequestDto.getDepartment())
                .build();
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getUsername())
                .build();
    }




}
