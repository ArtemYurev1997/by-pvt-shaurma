package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
