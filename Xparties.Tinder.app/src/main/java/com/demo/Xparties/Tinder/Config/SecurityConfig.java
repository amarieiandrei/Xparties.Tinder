package com.demo.Xparties.Tinder.Config;

import com.demo.Xparties.Tinder.Security.Handlers.CustomAuthenticationSuccessHandler;
import com.demo.Xparties.Tinder.Security.JWT.JwtAuthenticationFilter;
import com.demo.Xparties.Tinder.Security.OAuth2.CustomOAuth2UserService;
import com.demo.Xparties.Tinder.Security.OAuth2.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;
    // TODO: Handle failure
//    private final CustomAuthenticationFailureHandler failureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                 TODO: Add if only someone recommand
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/login").permitAll();
                    auth.anyRequest().authenticated();
                })
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> {
//                    oauth2.loginPage("https://www.xpartiestinder.com");
                    oauth2.userInfoEndpoint(userInfoEndpointConfig -> {
                        userInfoEndpointConfig.userService(customOAuth2UserService); // For Github, Facebook
                        userInfoEndpointConfig.oidcUserService(customOidcUserService); // For Google (OIDC)
                    });
                    oauth2.successHandler(successHandler);
//                     TODO: handle failure
//                    oauth2.failureHandler(failureHandler);
                })
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("https://www.xpartiestinder.com"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        // Expose headers so the frontend can access them
        config.setExposedHeaders(List.of("Authorization"));
        // Allow credentials (important for OAuth2 cookies)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}