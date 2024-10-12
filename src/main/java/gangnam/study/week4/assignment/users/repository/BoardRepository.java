package gangnam.study.week4.assignment.users.repository;

import gangnam.study.week4.assignment.users.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}