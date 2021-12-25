package com.api.clubhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.api.clubhouse.utils.Util.generateRandomPassword;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Club {
    @Id
    @SequenceGenerator(
            name = "club_sequence",
            sequenceName = "club_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "club_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Builder.Default
    private String dp="https://www.ecpgr.cgiar.org/fileadmin/templates/ecpgr.org/Assets/images/No_Image_Available.jpg";

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private String joiningPassword=generateRandomPassword(6);

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createdDate=new Date();

    @ManyToMany(mappedBy = "myClubs")
    private Set<User> members;

    @ElementCollection
    private Set<Chat>chats;

//    @ManyToOne(
//            targetEntity = User.class,
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "created_by",
//            referencedColumnName = "id"
//    )
    private Long createdBy;

    @OneToMany(
            targetEntity = Meeting.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "id"
    )
    private List<Meeting> meetings;

    @OneToMany(
            targetEntity = Activity.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "id"
    )
    private List<Activity> activities;

    @OneToMany(
            targetEntity = Post.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "id"
    )
    private List<Post> posts;


}
