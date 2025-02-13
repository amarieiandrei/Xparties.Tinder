package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndProviderId(String email, String providerId);

    Optional<UserDetails> findByEmail(String email);
}
