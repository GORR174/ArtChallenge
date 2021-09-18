package net.catstack.artchallenge.controller;

import net.catstack.artchallenge.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("files")
public class ImageFileSystemController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public FileSystemResource getImage(@PathVariable String imageName) {
        return imageService.getImageResource(imageName);
    }
}
