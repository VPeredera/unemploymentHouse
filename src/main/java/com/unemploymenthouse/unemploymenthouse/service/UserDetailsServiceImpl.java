package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.login.MyUserDetails;
import com.unemploymenthouse.unemploymenthouse.login.User;
import com.unemploymenthouse.unemploymenthouse.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.persistence.EntityNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null){
            throw new EntityNotFoundException("Could not find user!");
        }
        return new MyUserDetails(user);
    }
}
