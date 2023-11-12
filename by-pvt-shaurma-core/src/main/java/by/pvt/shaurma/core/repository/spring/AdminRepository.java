package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Query("select a from Admin a where a.login=:login and a.password=:password")
    Admin authorise(@Param("login") String login, @Param("password") String password);

//    @Modifying
//    @Query("update Admin a set a.login=:login and a.password=:password where a.id=:id")
//    void update(@Param("login") String login, @Param("password") String password, @Param("id") Long id);
}
