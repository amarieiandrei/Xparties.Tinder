package com.demo.Xparties.Tinder.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private Integer id;

    @Column(name = "external_id", nullable = false, updatable = false, unique = true)
    private String externalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Photo> photos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons;
}
