package org.zerock.covesthymeleaf.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/hello")
    public String[] hello(){
        return new String[]{"hello", "world"};
    }

    @GetMapping("/ex1")
    public void ex1(){
        log.info("ex1............");
    }

    @GetMapping("/exLayout")
    public void exLayout() {
        log.info("exLayout..........");
    }

    @GetMapping({"/exLayout1", "/exLayout2", "/exTemplate", "/exSidebar"})
    public void exLayout1(){
        log.info("exLayout.........");
    }
}
