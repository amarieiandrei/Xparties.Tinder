package com.demo.Xparties.Tinder.Security.FactoryPattern;

import com.demo.Xparties.Tinder.Exception.OAuth2Exception.OAuth2ProviderNotSupported;
import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;

import java.util.Map;

public class OAuth2UserDetailFactory {

    public static OAuth2UserDetails getOAuth2UserDetail(String registrationId, Map<String, Object> attributes) {
        String registrationIdUpperCase = registrationId.toUpperCase();

        if (registrationIdUpperCase.equals(OAuth2Provider.GITHUB.toString())) {
            return new OAuth2GithubUser(attributes);
        } else if (registrationIdUpperCase.equals(OAuth2Provider.GOOGLE.toString())) {
            return new OAuth2GoogleUser(attributes);
        } else if (registrationIdUpperCase.equals(OAuth2Provider.FACEBOOK.toString())) {
            return new OAuth2FacebookUser(attributes);
        } else {
            throw new OAuth2ProviderNotSupported("Sorry! Unsupported OAuth2 provider with a registration id " + registrationId.toUpperCase());
        }
    }
}