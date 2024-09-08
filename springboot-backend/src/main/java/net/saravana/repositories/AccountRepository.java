package net.saravana.repositories;

import net.saravana.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameAndPassword(String userName, String password);
    Optional<User> findByEmail(String email);
}
