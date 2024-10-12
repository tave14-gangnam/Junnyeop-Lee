package gangnam.study.week4.assignment.users.domain.entity;


import gangnam.study.week4.assignment.users.domain.dto.UserRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity { // BaseEntity 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    // CascadeType.ALL로 자식인 Board와 Comment의 생명주기를 관리
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;  // 유저가 속한 부서


    // 유저 정보 수정 메서드 (UserUpdateRequestDto 기반)
    public void updateUserInfo(UserUpdateRequestDto userUpdateRequestDto) {
        if (userUpdateRequestDto.getUsername() != null) {
            this.username = userUpdateRequestDto.getUsername();
        }

        if (userUpdateRequestDto.getEmail() != null) {
            this.email = userUpdateRequestDto.getEmail();
        }

        if (userUpdateRequestDto.getDepartment() != null) {
            this.department = userUpdateRequestDto.getDepartment();
        }
    }

    // 연관관계 메서드: Board 추가
    public void addBoard(Board board) {
        this.boards.add(board);  // 부모 객체에서 자식 리스트에 추가
        if (board.getUser() != this) {
            board.addUser(this);  // 자식 객체에서 부모를 설정 (양방향 관계 설정)
        }
    }

    // 연관관계 메서드: Board 제거
    public void removeBoard(Board board) {
        this.boards.remove(board);  // 부모 객체에서 자식 리스트에서 삭제
        if (board.getUser() == this) {
            board.addUser(null);  // 자식 객체에서 부모와의 관계를 제거
        }
    }

    // 연관관계 메서드: Comment 추가
    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getUser() != this) {
            comment.addUser(this); // Comment에도 User 설정
        }
    }
}
