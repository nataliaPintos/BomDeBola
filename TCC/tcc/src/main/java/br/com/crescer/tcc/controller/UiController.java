package br.com.crescer.tcc.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jacob.stein
 */

@Controller
public class UiController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {

        byte[] bytes;

        if (!file.isEmpty()) {
             bytes = file.getBytes();
            //store file in storage
        }

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }

}