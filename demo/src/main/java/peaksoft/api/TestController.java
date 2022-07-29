package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
@Tag(name = "Test controller",description = "User with role ADMIN and EDITOR can authenticate")
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String test(){
        return "hello admin";
    }
}
