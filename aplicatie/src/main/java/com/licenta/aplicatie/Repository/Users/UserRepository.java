package com.licenta.aplicatie.Repository.Users;

import com.licenta.aplicatie.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value ="SELECT * FROM User  WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);
}
