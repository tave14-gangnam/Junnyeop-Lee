package gangnam.study.week4.assignment.users.domain.entity;


import gangnam.study.week4.assignment.users.domain.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity { // BaseEntity 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    private String department;

    // 게시글 정보 수정 메서드
    public void updateBoardInfo(BoardRequestDto boardRequestDto) {
        if (boardRequestDto.getTitle() != null) {
            this.title = boardRequestDto.getTitle();
        }

        if (boardRequestDto.getContent() != null) {
            this.content = boardRequestDto.getContent();
        }
    }

    // 연관관계 메서드: 작성자(User) 설정
    public void addUser(User user) {
        this.user = user;
        if (user != null && !user.getBoards().contains(this)) {
            user.addBoard(this);  // 부모 객체의 자식 리스트에도 이 게시글을 추가
        }
    }

    // 연관관계 메서드: Comment 추가
    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getBoard() != this) {
            comment.addBoard(this); // Comment에도 Board 설정
        }
    }
}
