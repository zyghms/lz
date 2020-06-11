package com.zygh.lz.controller;

import com.zygh.lz.service.CfkjdjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Component
@RestController
public class CfkjdjController {

    @Autowired
    CfkjdjService cfkjdjService;


    //@Scheduled(cron = "0 0/10 * * * ?")
    @RequestMapping("/notSignIn")
    public void notSignIn() throws IOException {
        cfkjdjService.notSignIn();
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void notSignBack() throws IOException {
        cfkjdjService.notSignBack();
    }

}
