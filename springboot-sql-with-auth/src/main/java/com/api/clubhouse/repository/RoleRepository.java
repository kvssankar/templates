package com.api.clubhouse.repository;

import java.util.Optional;

import com.api.clubhouse.models.ERole;
import com.api.clubhouse.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

//    INSERT INTO roles(name) VALUES('ROLE_USER');
//    INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
//    INSERT INTO roles(name) VALUES('ROLE_ADMIN');