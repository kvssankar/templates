package com.api.clubhouse.controller;

import com.api.clubhouse.models.Club;
import com.api.clubhouse.models.User;
import com.api.clubhouse.payload.request.JoinClubRequest;
import com.api.clubhouse.repository.ClubRepository;
import com.api.clubhouse.repository.RoleRepository;
import com.api.clubhouse.repository.UserRepository;
import com.api.clubhouse.security.UserDetailsImpl;
import com.api.clubhouse.service.ClubService;
import com.api.clubhouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/club")
@AllArgsConstructor
public class ClubController {

    private final UserService userService;
    private final ClubService clubService;

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> addClub(@Valid @RequestBody Club club){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user=userRepository.getById(userDetails.getId());
        return ResponseEntity.ok(clubService.createClub(user,club));
    }

    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> joinClub(@Valid @RequestBody JoinClubRequest joinClubRequest){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user=userRepository.getById(userDetails.getId());
        user=clubService.joinClub(user, joinClubRequest.getId(), joinClubRequest.getJoiningPassword());
        if(user==null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid credentials");
        return ResponseEntity.ok(user);
    }
}
