package org.sb.proxy;

import com.azure.core.annotation.Get;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class ProxyApplication {

    @GetMapping("/")
    public String hello() {
        System.out.print(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
