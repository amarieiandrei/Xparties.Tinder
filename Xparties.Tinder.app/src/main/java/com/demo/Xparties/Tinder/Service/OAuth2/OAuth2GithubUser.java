package com.demo.Xparties.Tinder.Service.OAuth2;

import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;

import java.util.Map;

public class OAuth2GithubUser extends OAuth2UserDetails {

    public OAuth2GithubUser(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.GITHUB;
    }
}
