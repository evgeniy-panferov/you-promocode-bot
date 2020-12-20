package ru.youpromocodebot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.util.ResourceUtils.getFile;

@RestController
@CrossOrigin(value = "https://tg-bot-site.herokuapp.com/")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping("api/image/{imageName}")
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(@PathVariable String imageName) {
        log.info("ImageController getPhoto imageName - {}", imageName);
        byte[] image = new byte[0];
        try {
            image = org.apache.commons.io.FileUtils.readFileToByteArray(getFile("classpath:image/" + imageName + ".jpg"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
}
