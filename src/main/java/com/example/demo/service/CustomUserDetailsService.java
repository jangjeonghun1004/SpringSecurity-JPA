package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUser = this.userRepository.findByUserid(username);

        if(findUser.isPresent()) {
            User user = findUser.get();
            if(user.getUserid().equals(username)) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(user.getUserid())
                        .password(user.getPassword())
                        .roles(user.getAuthorities().stream()
                                .map(Authority::getName)
                                .toArray(String[]::new)
                        )
                        .build();
            }
        }

        throw new UsernameNotFoundException("User not found");
    }
}
