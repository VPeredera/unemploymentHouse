package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.login.MyUserDetails;
import com.unemploymenthouse.unemploymenthouse.login.User;
import com.unemploymenthouse.unemploymenthouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
   public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null){
            throw new EntityNotFoundException("Could not find user!");
        }
        return new MyUserDetails(user);
    }
}
