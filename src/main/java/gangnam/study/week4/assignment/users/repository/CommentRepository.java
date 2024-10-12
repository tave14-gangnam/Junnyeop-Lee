package gangnam.study.week4.assignment.users.repository;

import gangnam.study.week4.assignment.users.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
