package com.api.clubhouse.repository;

import com.api.clubhouse.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubRepository extends JpaRepository<Club,Long> {
    @Query(
            value = "select c from Club c where c.id=:id and c.joiningPassword=:jp"
    )
    public Club getClubByIdAndJoiningPassword(
            @Param("id") Long id,
            @Param("jp") String jp);
}
