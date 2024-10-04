package gangnam.study.week4.assignment.users.domain.userMapper;

import gangnam.study.week4.assignment.users.domain.dto.UserRequest;
import gangnam.study.week4.assignment.users.domain.dto.UserResponse;
import gangnam.study.week4.assignment.users.domain.entity.User;

public class UserMapper {


    public static User toUserSignup(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .build();
    }


}
