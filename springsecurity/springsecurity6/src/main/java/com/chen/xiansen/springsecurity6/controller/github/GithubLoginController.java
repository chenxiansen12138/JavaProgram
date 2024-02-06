package com.chen.xiansen.springsecurity6.controller.github;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GithubLoginController {
    @GetMapping("/login")
    public String index(Model model,
                        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                        @AuthenticationPrincipal OAuth2User oAuth2User) {
        model.addAttribute("userName", oAuth2User.getName());
        model.addAttribute("clientName", client.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oAuth2User.getAttributes());
        return "index";
    }
}
