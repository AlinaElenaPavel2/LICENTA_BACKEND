package com.licenta.aplicatie.Controller;


import com.licenta.aplicatie.Models.User;
import com.licenta.aplicatie.Service.UserService;
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
            role = userService.getRoleByCredentials(user.getUsername(), user.getParola());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
