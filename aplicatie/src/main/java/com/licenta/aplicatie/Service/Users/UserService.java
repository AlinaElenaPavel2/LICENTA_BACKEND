package com.licenta.aplicatie.Service.Users;

import com.licenta.aplicatie.Models.Users.User;
import com.licenta.aplicatie.Repository.Users.StudentRepository;
import com.licenta.aplicatie.Repository.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@Transactional
public class UserService implements UserDetailsService {
    //    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getRoleByCredentials(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        System.out.println("******* USER SERVICE *******");
        System.out.println(user);
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

    public String getProfilePicturePath(int id_user) throws Exception {
        String path = userRepository.getProfilePicturePath(id_user);
        if (path != null) {
            return path;
        } else {
            return userRepository.getProfilePicturePath(-1);
        }
    }

    public void addUser(User user) {
        System.out.println(user);
        userRepository.save(user);
    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username); //userDAO == null Causing NPE
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), emptyList());

    }

    public void updateUserPassword(Integer id,String password)
    {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent())
        {
            user.get().setPassword(password);
        }
    }

}
