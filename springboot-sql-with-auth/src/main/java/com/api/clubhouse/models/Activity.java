package com.api.clubhouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
    @Id
    @SequenceGenerator(
            name="activity_sequence",
            sequenceName = "activity_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDate;

    @Builder.Default
    private Integer duration=1;

    @OneToOne
    @JoinColumn(name = "id")
    private User assignedTo;

    @Builder.Default
    private Integer status=0;

}
