package com.demo.Xparties.Tinder.Security.FactoryPattern;

import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;

import java.util.Map;

public class OAuth2GoogleUser extends OAuth2UserDetails {

    public OAuth2GoogleUser(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
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
        return OAuth2Provider.GOOGLE;
    }
}
