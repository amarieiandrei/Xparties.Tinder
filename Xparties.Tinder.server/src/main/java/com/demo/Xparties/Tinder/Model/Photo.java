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
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "external_id", nullable = false, updatable = false, unique = true)
    private String externalId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "data", nullable = false)
    private byte[] data;

    @OneToOne(mappedBy = "photo")
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;
}