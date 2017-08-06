package cn.niceabc.springbootadminclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping("/")
    public String index() throws InterruptedException {
        Thread.sleep(1000);
        return "web.";
    }
}
