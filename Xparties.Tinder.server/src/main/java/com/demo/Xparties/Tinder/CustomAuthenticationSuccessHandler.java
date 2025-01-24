package com.demo.Xparties.Tinder;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            String clientRegistrationId = oauth2Token.getAuthorizedClientRegistrationId();

            if (clientRegistrationId.equals("google")) {
                // Google OAuth2
                // Extract id_token & claims

                OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

                String idToken = oidcUser.getIdToken().getTokenValue();
                Map<String, Object> claims = oidcUser.getIdToken().getClaims();
                System.out.println("Google ID Token: " + idToken);
                System.out.println("Google Claims: " + claims);

                // You can use the idToken for further processing
            } else if (clientRegistrationId.equals("github")) {
                // GitHub OAuth2
                // Extract accessToken & claims

                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                // Retrieve GitHub access token
                OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                        clientRegistrationId, oauth2Token.getName());
                if (authorizedClient != null) {
                    String accessToken = authorizedClient.getAccessToken().getTokenValue();
                    System.out.println("GitHub Access Token: " + accessToken);
                }

                Map<String, Object> claims = oAuth2User.getAttributes();
                System.out.println("GitHub Claims: " + claims);

                // You can use the accessToken for further processing
            }
        }
        // Redirect to the home page or any other endpoint
        response.sendRedirect("/");
    }
}