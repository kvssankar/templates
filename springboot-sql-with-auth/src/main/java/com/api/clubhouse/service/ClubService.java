package com.api.clubhouse.service;

import com.api.clubhouse.models.Club;
import com.api.clubhouse.models.User;
import com.api.clubhouse.repository.ClubRepository;
import com.api.clubhouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    public User createClub(User user,Club club){
        Club newClub=Club.builder()
                .name(club.getName())
                    .password(club.getPassword())
                        .description(club.getDescription())
                            .createdBy(user.getId())
                                .build();
        user.addCreatedClub(newClub);
        return userRepository.save(user);
    }

    public User joinClub(User user,Long cid,String jp){
        Club club=clubRepository.getClubByIdAndJoiningPassword(cid,jp);
        if(club==null) return null;
        user.addMyClub(club);
        userRepository.save(user);
        return user;
    }
}
