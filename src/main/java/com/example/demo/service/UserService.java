package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

public interface UserService {
   User save(User user);
}
