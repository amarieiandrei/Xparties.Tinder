package com.demo.Xparties.Tinder.Service.User;

import com.demo.Xparties.Tinder.Exception.UserException.UserNotFound;
import com.demo.Xparties.Tinder.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("user with email " + email + " could not be found."));
    }
}
