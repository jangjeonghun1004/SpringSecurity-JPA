package com.example.demo.service;

import com.example.demo.entity.Authority;

import java.util.Optional;

public interface AuthorityService {
    Authority save(Authority authority);
    Optional<Authority> findByName(String name);
}
