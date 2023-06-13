package peter.lee.environmentvariable.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {
    @Value("${environment.home}")
    private String home;

    @Value("${environment.shell}")
    private String shell;

    @Value("${environment.user}")
    private String user;

    @GetMapping("/environment/{variable}")
    ResponseEntity<String> all(@PathVariable String variable) {
        switch (variable) {
            case "home":
                return ResponseEntity.ok(home);
            case "shell":
                return ResponseEntity.ok(shell);
            case "user":
                return ResponseEntity.ok(user);
            default:
                return ResponseEntity.notFound().build();
        }
    }
}
