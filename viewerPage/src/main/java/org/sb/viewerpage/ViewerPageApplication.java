package org.sb.viewerpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ViewerPageApplication {

    @GetMapping("/")
    public String home() {
        return "Welcome to Viewer Page";
    }
    public static void main(String[] args) {
        SpringApplication.run(ViewerPageApplication.class, args);
    }

}
