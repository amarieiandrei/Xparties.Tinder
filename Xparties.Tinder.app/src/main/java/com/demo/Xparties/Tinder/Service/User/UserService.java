package com.demo.Xparties.Tinder.Service.User;

import com.demo.Xparties.Tinder.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

    private final UserRepository userRepository;
}
