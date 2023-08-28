package ir.jimsa.cek.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckController {

    @GetMapping
    public String getCheckStatus() {
        return this.getClass().getName() + " it works!";
    }
}
