package com.api.clubhouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meeting {
    @Id
    @SequenceGenerator(
            name="meeting_sequence",
            sequenceName = "meeting_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meeting_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    //TODO: IMPLEMENT ACKNOWLEDGED USING NESTED ATTRIBUTE AND MAP IT
    @OneToMany(
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            mappedBy = "id"
    )
    private List<User> attended;

}
