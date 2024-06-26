package org.sb.adminpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AdminPageApplication {

    @GetMapping("/")
    public String index() {
        return "Welcome to Admin Page";
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminPageApplication.class, args);
    }

}
