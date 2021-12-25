package com.api.clubhouse.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String username;

    private Long phoneNumber;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private String dp="https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";

    private int year;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createdDate=new Date();

    @OneToMany(
            targetEntity = Club.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "id"
    )
    private Set<Club>createdClubs;

    @ManyToMany
    @JoinTable(
            name = "my_clubs",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "club_id",referencedColumnName = "id"))
    private Set<Club> myClubs;

    @ManyToMany
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addCreatedClub(Club c) {
        c.setCreatedBy(id);
        if(createdClubs==null) createdClubs=new HashSet<>();
        createdClubs.add(c);
    }

    public void addMyClub(Club c) {
        if(myClubs==null) myClubs=new HashSet<>();
        myClubs.add(c);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
