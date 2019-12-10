package gawlowski.maciej.example.controller;

import lombok.extern.slf4j.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
public class InsecureController {

    @ResponseStatus(value = OK)
    @GetMapping(value = "/insecured")
    public void insecured() {
        log.info("Insecured");
    }
}
