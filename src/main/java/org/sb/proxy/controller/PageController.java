package org.sb.proxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PageController {

    @GetMapping
    public RedirectView redirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://www.google.com");
        redirectView.setContextRelative(true);
        return redirectView;
    }
}
