package com.kisonich.learntest.magaz.repository;

import com.kisonich.learntest.magaz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    User findByLogin(String login);

}