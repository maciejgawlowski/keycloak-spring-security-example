package gawlowski.maciej.example.controller;

import lombok.extern.slf4j.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
public class SecuredController {

    @ResponseStatus(value = OK)
    @GetMapping(value = "/secured")
    public void secured() {
        log.info("Secured");
    }

}
