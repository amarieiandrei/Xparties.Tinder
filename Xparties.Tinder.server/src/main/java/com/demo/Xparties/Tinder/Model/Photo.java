package com.demo.Xparties.Tinder.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "photos")
public class Photo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private Long id;

    @Column(name = "external_id", nullable = false, updatable = false, unique = true)
    private String externalId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @JsonIgnore
    @Column(name = "data", nullable = false)
    private byte[] data;

    // TODO: POSTMAN exception inspect
    @JsonIgnore
    @ManyToOne()
    private Event event;

    // TODO: POSTMAN exception inspect
    @JsonIgnore
    @ManyToOne()
    private Person person;
}