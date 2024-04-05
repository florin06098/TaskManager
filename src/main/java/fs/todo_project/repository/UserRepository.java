package fs.todo_project.repository;
import fs.todo_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /*
        Used only by the JwtFilter
     */
    @Query("SELECT new Users(u.id, u.name, u.email, u.password, u.roles) FROM Users u WHERE u.name = :username")
    Optional<User> findByName(@Param("username") String username);

}