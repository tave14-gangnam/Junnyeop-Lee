package gangnam.study.week4.assignment.users.service;

import gangnam.study.week4.assignment.users.domain.dto.CommentRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.CommentResponseDto;
import gangnam.study.week4.assignment.users.domain.entity.Board;
import gangnam.study.week4.assignment.users.domain.entity.Comment;
import gangnam.study.week4.assignment.users.domain.entity.User;
import gangnam.study.week4.assignment.users.domain.userMapper.CommentMapper;
import gangnam.study.week4.assignment.users.repository.BoardRepository;
import gangnam.study.week4.assignment.users.repository.CommentRepository;
import gangnam.study.week4.assignment.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long userId, Long boardId) {
        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 유저가 존재하지 않습니다."));

        // 게시판 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 게시판이 존재하지 않습니다."));

        // 댓글 생성
        Comment comment = CommentMapper.toEntity(requestDto, user, board);


        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        return CommentMapper.toResponseDto(savedComment);
    }

    // 댓글 조회 (ID로 조회)
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 댓글이 존재하지 않습니다."));
        return CommentMapper.toResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 댓글이 존재하지 않습니다."));

        // 댓글 내용 업데이트
        comment.updateContent(requestDto.getContent());

        return CommentMapper.toResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);  // 댓글 삭제
    }
}
