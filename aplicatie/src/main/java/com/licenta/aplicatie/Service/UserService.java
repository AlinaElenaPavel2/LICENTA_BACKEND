package com.licenta.aplicatie.Service;

import com.licenta.aplicatie.Models.User;
import com.licenta.aplicatie.Repository.UserRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String getRoleByCredentials(String username,String password) throws Exception {
        User user=userRepository.findByUsername(username);
    if(user!=null && password.equals(user.getParola()))
        return user.getRol();
    else
        throw  new Exception("Wrong credentials");
    }

    public Integer getIdByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if( user!=null )
            return user.getId();
        else
            throw new Exception("Wrong credentials");
    }
}
