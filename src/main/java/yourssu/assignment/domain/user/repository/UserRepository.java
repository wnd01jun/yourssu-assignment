package yourssu.assignment.domain.user.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yourssu.assignment.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
        email 중복 검증을 위해 사용
     */
    Optional<User> findByEmail(String email);

}
