package tech.one.place.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.one.place.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);
    User findByEmail(String email);
}
