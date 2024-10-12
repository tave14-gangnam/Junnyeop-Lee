package gangnam.study.week4.assignment.users.service;

import gangnam.study.week4.assignment.users.domain.dto.BoardRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.BoardResponseDto;
import gangnam.study.week4.assignment.users.domain.entity.Board;
import gangnam.study.week4.assignment.users.domain.entity.User;
import gangnam.study.week4.assignment.users.domain.userMapper.BoardMapper;
import gangnam.study.week4.assignment.users.repository.BoardRepository;
import gangnam.study.week4.assignment.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시글 생성
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, Long userId) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 유저가 존재하지 않습니다."));

        // // DTO를 board 엔티티로 변환

        Board board = BoardMapper.toBoard(boardRequestDto, user);  // board.addUser(user) 내부에서 user.addBoard(board) 호출됨

        // board 저장
        Board savedBoard = boardRepository.save(board);

        return BoardMapper.toBoardResponseDto(savedBoard);
    }

    // 게시글 조회 (ID로 조회)
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 게시글이 존재하지 않습니다."));
        return BoardMapper.toBoardResponseDto(board);
    }

    // 게시글 수정 (ID로 조회 후 수정)
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 게시글이 존재하지 않습니다."));

        existingBoard.updateBoardInfo(boardRequestDto);
        Board updatedBoard = boardRepository.save(existingBoard);

        return BoardMapper.toBoardResponseDto(updatedBoard);
    }

    // 게시글 삭제 (ID로 삭제)
    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }
}
