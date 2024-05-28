package org.sb.proxy;

import com.azure.core.annotation.Get;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@SpringBootApplication
@RestController
public class ProxyApplication {

    @GetMapping("/")
    public RedirectView hello() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.print(principal + " ");


        // Call GraphAPI here


        // Authorization check here


        return new RedirectView("/index.html");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
