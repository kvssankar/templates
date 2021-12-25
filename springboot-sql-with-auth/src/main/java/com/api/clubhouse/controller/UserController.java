package com.api.clubhouse.controller;

import com.api.clubhouse.models.ERole;
import com.api.clubhouse.models.Role;
import com.api.clubhouse.models.User;
import com.api.clubhouse.payload.request.LoginRequest;
import com.api.clubhouse.payload.request.SignupRequest;
import com.api.clubhouse.payload.response.JwtResponse;
import com.api.clubhouse.payload.response.MessageResponse;
import com.api.clubhouse.repository.RoleRepository;
import com.api.clubhouse.repository.UserRepository;
import com.api.clubhouse.security.JwtUtils;
import com.api.clubhouse.security.UserDetailsImpl;
import com.api.clubhouse.service.ClubService;
import com.api.clubhouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ClubService clubService;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<User>all(){
        return userRepository.findAll();
    }



}
