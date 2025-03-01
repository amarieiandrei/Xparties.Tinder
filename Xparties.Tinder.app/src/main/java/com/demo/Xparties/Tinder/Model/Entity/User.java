package com.demo.Xparties.Tinder.Model.Entity;

import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuth2Provider provider;

    // TODO: REFACTOR WITH Custom User Details
    // Add authorities as a transient field (not persisted in the database)
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    // Method to return authorities (empty collection)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptyList();
    }

    // TODO: I want to have the information stored in database not in name but in firstName and lastName
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
}