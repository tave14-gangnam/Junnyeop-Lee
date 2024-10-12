package gangnam.study.week4.assignment.users.domain.userMapper;

import gangnam.study.week4.assignment.users.domain.dto.CommentRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.CommentResponseDto;
import gangnam.study.week4.assignment.users.domain.entity.Board;
import gangnam.study.week4.assignment.users.domain.entity.Comment;
import gangnam.study.week4.assignment.users.domain.entity.User;

public class CommentMapper {


        // Comment -> CommentResponseDto 변환 (빌더 패턴 사용)
        public static CommentResponseDto toResponseDto(Comment comment) {
            return CommentResponseDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .username(comment.getUser().getUsername())
                    .boardTitle(comment.getBoard().getTitle())
                    .build();
        }

        // CommentRequestDto -> Comment 변환 (빌더 패턴 사용)
    /*
    연관관계 편의 메서드 사용: "어떤 게시글의 댓글을 가져올 때, 그 댓글은 그 게시글을 알고 있어야 하고, 게시글도 그 댓글을 알고 있어야 해."

    그러니깐 직관적으로 알기 쉬운정도 ? 인 것 같다.


     */
        public static Comment toEntity(CommentRequestDto requestDto, User user, Board board) {
            Comment comment = Comment.builder()
                    .content(requestDto.getContent())
                    .build();
            comment.addUser(user);
            comment.addBoard(board);
            return comment;
        }

}
