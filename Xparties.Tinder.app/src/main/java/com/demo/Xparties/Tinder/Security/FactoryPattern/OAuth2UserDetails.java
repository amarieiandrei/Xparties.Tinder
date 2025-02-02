package com.demo.Xparties.Tinder.Security.FactoryPattern;

import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;

import java.util.Map;

public abstract class OAuth2UserDetails {

    protected Map<String, Object> attributes;

    public OAuth2UserDetails(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getProviderId();

    public abstract String getEmail();

    public abstract String getName();

    public abstract OAuth2Provider getProvider();
}