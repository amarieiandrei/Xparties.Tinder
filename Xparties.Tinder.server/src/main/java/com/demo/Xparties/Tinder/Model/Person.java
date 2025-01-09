package com.demo.Xparties.Tinder.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private Long id;

    @Column(name = "external_id", nullable = false, updatable = false, unique = true)
    private String externalId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Photo> photos;

    @JsonIgnore
    @ManyToMany(mappedBy = "persons")
    private List<Event> events;
}