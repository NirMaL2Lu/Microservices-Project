package com.microservice.apigateway.controllers;

import com.microservice.apigateway.models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient authorizedClient,
           @AuthenticationPrincipal OidcUser user,
           Model model
    ) {

        logger.info("user email id : {} ",user.getEmail());
        //creating auth response object
        AuthResponse authResponse = new AuthResponse();
        //setting email to auth response

        authResponse.setUserId(user.getEmail());
        //setting token to auth response
        authResponse.setAccessToken(authorizedClient.getAccessToken().getTokenValue());
        //setting refresh token to auth response
        System.out.println("authorizedClient "+authorizedClient);

        if (authorizedClient != null && authorizedClient.getRefreshToken().getTokenValue() != null) {
            String refreshTokenValue = authorizedClient.getRefreshToken().getTokenValue();
            System.out.println("refreshTokenValue "+refreshTokenValue);
            // Your code to use refreshTokenValue
            authResponse.setRefreshToken(refreshTokenValue);
        } else {
            // Handle the case where refreshToken is null, perhaps by requesting a new token.
            System.out.println("Hii");
            authResponse.setRefreshToken(authorizedClient.getRefreshToken().getTokenValue());
        }

        //set expire time of token
        authResponse.setExpireAt(authorizedClient.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());

        authResponse.setAuthorities(authorities);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
