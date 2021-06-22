package com.licenta.aplicatie.Controller.Users;

import com.licenta.aplicatie.Models.Users.User;
import com.licenta.aplicatie.Service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/licenta")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResponseEntity<?> login(@RequestBody User user) {
        String role;
        try {
            role = userService.getRoleByCredentials(user.getUsername(), user.getPassword());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public ResponseEntity<?> addUser() {
        User user=new User();
        user.setId_student(1);
        user.setUsername("Buna");
        user.setPassword("Password");
        user.setRole("student");
        userService.addUser(user);
        System.out.println(user);
//        User user_db=userService.getUser(133);
        try {

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
