package gangnam.study.week4.assignment.controller;


import gangnam.study.week4.assignment.users.domain.dto.UserRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.UserResponseDto;
import gangnam.study.week4.assignment.users.domain.dto.UserUpdateRequestDto;
import gangnam.study.week4.assignment.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor  // final 필드를 이용한 생성자 자동 생성
public class UserController {

    private final UserService userService;

    /**
     * 유저 생성 (회원가입)
     *
     * [POST 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/users/signup
     * Method: POST
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "username": "testuser",
     *   "password": "password123",
     *   "email": "testuser@example.com",
     *   "role": "USER",
     *   "department": "DEVELOPMENT"
     * }
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody  UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.createUser(requestDto);
        return ResponseEntity.ok(userResponseDto);  // 200 OK 응답과 함께 유저 정보 반환
    }

    /**
     * 유저 조회 (ID로 조회)
     *
     * [GET 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/users/{id}
     * Method: GET
     * Content-Type: application/json
     * Path Variable: id (유저 ID)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDto);  // 200 OK 응답과 함께 유저 정보 반환
    }

    /**
     * 유저 수정 (ID로 수정)
     *
     * [PUT 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/users/{id}
     * Method: PUT
     * Content-Type: application/json
     * Body: raw JSON 형식
     * {
     *   "username": "updatedUser",
     *   "email": "updateduser@example.com",
     *   "role": "ADMIN",
     *   "department": "HR"
     * }
     * Path Variable: id (유저 ID)
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody  UserUpdateRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.ok(userResponseDto);  // 200 OK 응답과 함께 수정된 유저 정보 반환
    }

    /**
     * 유저 삭제 (ID로 삭제)
     *
     * [DELETE 요청 테스트 - Postman]
     * URL: http://localhost:8080/api/users/{id}
     * Method: DELETE
     * Content-Type: application/json
     * Path Variable: id (유저 ID)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();  // 204 No Content 응답 반환
    }
}
