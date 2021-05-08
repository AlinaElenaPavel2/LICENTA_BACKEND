package com.licenta.aplicatie.Service.Users;

import com.licenta.aplicatie.Models.Users.User;
import com.licenta.aplicatie.Repository.Users.StudentRepository;
import com.licenta.aplicatie.Repository.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String getRoleByCredentials(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            System.out.println(user.getRole());
            return user.getRole();
        } else
            throw new Exception("Wrong credentials");
    }

    public Integer getIdByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user.getId_user();
        else
            throw new Exception("Wrong credentials");
    }

    public Integer getUserIdByRole(int id, String role) throws Exception {
        if (role.equals("student")) {
            Integer user_id = userRepository.getUserIdByStudentId(id);
            if (user_id == null) {
                throw new Exception("It does not exist the user with that student id");
            } else {
                return user_id;
            }
        } else {
            Integer profesor_id = userRepository.getUserIdByProfesorId(id);

            if (profesor_id == null) {
                throw new Exception("It does not exist the user with that profesor id");
            } else {
                return profesor_id;
            }
        }

    }
}
