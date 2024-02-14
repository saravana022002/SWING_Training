package net.javaguides.auth.repository;

import net.javaguides.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUserNameAndPassword(String userName, String password);
    Account findByUserName(String username);
}
