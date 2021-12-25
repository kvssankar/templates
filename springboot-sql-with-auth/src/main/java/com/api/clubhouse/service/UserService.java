package com.api.clubhouse.service;

import com.api.clubhouse.models.User;
import com.api.clubhouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;




}
