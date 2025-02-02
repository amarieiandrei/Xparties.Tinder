package com.demo.Xparties.Tinder.Service.OAuth2;

import com.demo.Xparties.Tinder.Exception.OAuth2Exception.OAuth2UserNotFoundByProperties;
import com.demo.Xparties.Tinder.Model.Entity.User;
import com.demo.Xparties.Tinder.Repository.UserRepository;
import com.demo.Xparties.Tinder.Security.FactoryPattern.OAuth2UserDetailFactory;
import com.demo.Xparties.Tinder.Security.FactoryPattern.OAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        try {

            OAuth2User oAuth2User = super.loadUser(userRequest);

            String registrationId = userRequest.getClientRegistration().getRegistrationId();

            OAuth2UserDetails oAuth2UserDetails = OAuth2UserDetailFactory.getOAuth2UserDetail(registrationId, oAuth2User.getAttributes());

            if (ObjectUtils.isEmpty(oAuth2UserDetails)) {
                throw new OAuth2UserNotFoundByProperties("Can't found OAuth2 user by properties");
            }

            Optional<User> userOptional = userRepository.findByEmailAndProviderId(oAuth2UserDetails.getEmail(), oAuth2UserDetails.getProviderId());

            if (userOptional.isEmpty()) {
                User newUser = new User();

                newUser.setProviderId(oAuth2UserDetails.getProviderId());
                newUser.setEmail(oAuth2UserDetails.getEmail());
                newUser.setName(oAuth2UserDetails.getName());
                newUser.setProvider(oAuth2UserDetails.getProvider());

                userRepository.save(newUser);
            }

            System.out.println("MALIAKADEMY MAFIA1");
            return oAuth2User;

        } catch (AuthenticationException e) {

            System.out.println("MALIAKADEMY 1");
            throw e;

        } catch (Exception ex) {

            System.out.println("MALIAKADEMY 2");
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);

        }
    }
}