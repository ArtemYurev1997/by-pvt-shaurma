package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.login=:login")
    User loadUserByUserName(@Param("login") String login);
}
