package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService{
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority save(Authority authority) {
        return this.authorityRepository.save(authority);
    }

    @Override
    public Optional<Authority> findByName(String name) {
        return this.authorityRepository.findByName(name);
    }
}
