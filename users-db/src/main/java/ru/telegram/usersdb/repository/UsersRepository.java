package ru.telegram.usersdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.telegram.usersdb.model.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE telegram_id=:id ORDER BY date_time DESC LIMIT 1")
    @Override
    Optional<Users> findById(@Param("id") Long aLong);
}
