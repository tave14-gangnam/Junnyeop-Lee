package gangnam.study.week4.assignment.users.service;

import gangnam.study.week4.assignment.users.domain.dto.UserRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.UserResponse;
import gangnam.study.week4.assignment.users.domain.dto.UserResponseDto;
import gangnam.study.week4.assignment.users.domain.dto.UserUpdateRequestDto;
import gangnam.study.week4.assignment.users.domain.entity.User;
import gangnam.study.week4.assignment.users.domain.userMapper.UserMapper;
import gangnam.study.week4.assignment.users.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    // 유저 생성 (회원가입) 로직
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new EntityExistsException("이미 등록된 이메일입니다.");  // 중복 이메일 예외 처리
        }

        // DTO를 User 엔티티로 변환
        User user = UserMapper.toUserSignup(userRequestDto);

        // User 엔티티 저장
        User savedUser = userRepository.save(user);

        // 저장된 User 엔티티를 UserResponseDto로 변환하여 반환
        return UserMapper.toUserResponseDto(savedUser);
    }

    // 유저 조회 (ID로 조회)
    @Transactional
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 유저가 존재하지 않습니다."));
        return UserMapper.toUserResponseDto(user);
    }

    // 유저 수정 (ID로 조회 후 수정)
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        // 기존 유저를 찾고 없으면 예외 발생
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 유저가 존재하지 않습니다."));

        // 유저 정보 수정 (엔티티 내부 메서드 사용)
        existingUser.updateUserInfo(userUpdateRequestDto);

        // 수정된 User 저장
        User updatedUser = userRepository.save(existingUser);

        // 수정된 유저 정보를 DTO로 변환하여 반환
        return UserMapper.toUserResponseDto(updatedUser);
    }


    // 유저 삭제 (ID로 삭제)
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 유저가 존재하지 않습니다."));
        userRepository.delete(user);
    }

}
