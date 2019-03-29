package com.example.pikkonsultacje;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping("/index")
    public String getIndex() {
        return "Index";
    }
}
