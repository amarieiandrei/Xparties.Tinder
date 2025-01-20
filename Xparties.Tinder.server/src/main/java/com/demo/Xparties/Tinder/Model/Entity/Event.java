package com.demo.Xparties.Tinder.Model.Entity;

import com.demo.Xparties.Tinder.Model.Enums.EventCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(name = "external_id", nullable = false, updatable = false, unique = true)
    private String externalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id", unique = true)
    private Photo photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new LinkedHashSet<>();
}
