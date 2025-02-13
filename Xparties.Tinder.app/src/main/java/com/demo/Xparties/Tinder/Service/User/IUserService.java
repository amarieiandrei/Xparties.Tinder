package com.demo.Xparties.Tinder.Service.User;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    UserDetails loadUserByEmail(String email);
}