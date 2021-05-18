package com.licenta.aplicatie.Repository.Users;

import com.licenta.aplicatie.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value ="SELECT * FROM User  WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);

    @Query(value ="SELECT id_user FROM User  WHERE id_student = :id_student", nativeQuery = true)
    Integer getUserIdByStudentId(int id_student);

    @Query(value ="SELECT id_user FROM User  WHERE id_profesor = :id_profesor", nativeQuery = true)
    Integer getUserIdByProfesorId(int id_profesor);

    @Query(value ="SELECT picture FROM User  WHERE id_user = :id_user", nativeQuery = true)
    String getProfilePicturePath(int id_user);
}
