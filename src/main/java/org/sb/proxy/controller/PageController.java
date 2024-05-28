package org.sb.proxy.controller;

import org.sb.proxy.service.AuthorizationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URISyntaxException;

@Controller
public class PageController {

    private final AuthorizationService authService;

    public PageController(AuthorizationService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public RedirectView redirect() throws URISyntaxException {
        authService.authorize();
        System.out.print(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new RedirectView("https://graph.microsoft.com/v1.0/me");
    }
}
