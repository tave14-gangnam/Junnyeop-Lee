package gangnam.study.week4.assignment.users.domain.userMapper;

import gangnam.study.week4.assignment.users.domain.dto.BoardRequestDto;
import gangnam.study.week4.assignment.users.domain.dto.BoardResponseDto;
import gangnam.study.week4.assignment.users.domain.entity.Board;
import gangnam.study.week4.assignment.users.domain.entity.User;

public class BoardMapper {

    // BoardRequestDto -> Board 변환 메서드
    public static Board toBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .department(boardRequestDto.getDepartment().getDescription())  // Enum 값을 문자열로 변환하여 저장
                .build();
        board.addUser(user);
        return board;
    }

    // Board -> BoardResponseDto 변환 메서드
    public static BoardResponseDto toBoardResponseDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getUser().getUsername())
                .build();

}
}
