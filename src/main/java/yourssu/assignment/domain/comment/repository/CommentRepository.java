package yourssu.assignment.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.user.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteAllByUser(@Param("user") User user);
}
