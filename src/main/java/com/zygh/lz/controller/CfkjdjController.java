package com.zygh.lz.controller;

import com.zygh.lz.service.CfkjdjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Component
@RestController
public class CfkjdjController {

<<<<<<< HEAD

=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
    @Autowired
    CfkjdjService cfkjdjService;


<<<<<<< HEAD
    @Scheduled(cron = "0 0/5 * * * ?")
=======
    //@Scheduled(cron = "0 0/10 * * * ?")
    @RequestMapping("/notSignIn")
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
    public void notSignIn() throws IOException {
        cfkjdjService.notSignIn();
    }

<<<<<<< HEAD
    /*@Scheduled(cron = "0 0/10 * * * ?")
    public void notSignBack() throws IOException {
        cfkjdjService.notSignBack();
    }*/
=======
    @Scheduled(cron = "0 0/30 * * * ?")
    public void notSignBack() throws IOException {
        cfkjdjService.notSignBack();
    }
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4

}
