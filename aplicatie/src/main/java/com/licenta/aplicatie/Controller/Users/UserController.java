package com.licenta.aplicatie.Controller.Users;

import com.licenta.aplicatie.Models.Users.User;
import com.licenta.aplicatie.Service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/licenta")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin
    @RequestMapping(value = "/role", method = {RequestMethod.POST})
    public ResponseEntity<?> login(@RequestBody User request) {
        String roleNotFound = "No Role Found";
        try {
            System.out.println(userService.getIdByUsername(request.getUsername()));
            User user = userService.getUser(userService.getIdByUsername(request.getUsername()));
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                String role = userService.getRoleByCredentials(request.getUsername(), user.getPassword());
                return new ResponseEntity<>(role, HttpStatus.OK);
            }
            return new ResponseEntity<>(roleNotFound, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/username={username}", method = {RequestMethod.PUT})
    public ResponseEntity<?> addUser(@PathVariable("username") String username) throws Exception {
        Integer id_user = userService.getIdByUsername(username);
        User user = userService.getUser(id_user);
        userService.updateUserPassword(id_user, bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        try {

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("user", HttpStatus.OK);
    }
}
