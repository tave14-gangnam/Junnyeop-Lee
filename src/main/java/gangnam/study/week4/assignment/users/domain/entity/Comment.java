package gangnam.study.week4.assignment.users.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity { // BaseEntity 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 연관관계 메서드: Board 설정
    public void addBoard(Board board) {
        this.board = board;
        if (!board.getComments().contains(this)) {
            board.addComment(this); // Board에도 Comment를 추가
        }
    }

    // 연관관계 메서드: User 설정
    public void addUser(User user) {
        this.user = user;
        if (!user.getComments().contains(this)) {
            user.addComment(this); // User에도 Comment를 추가
        }
    }

    // 댓글 내용 수정 메서드
    public void updateContent(String content) {
        this.content = content;
    }
}
