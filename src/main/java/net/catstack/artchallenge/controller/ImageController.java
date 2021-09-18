package net.catstack.artchallenge.controller;

import net.catstack.artchallenge.domain.Image;
import net.catstack.artchallenge.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getImages() {
        return imageService.getAllImages();
    }

    @GetMapping("{id}")
    public Image getImage(@PathVariable("id") Image image) {
        return image;
    }

    @PostMapping
    public Image uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        try {
            return saveImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Image saveImage(MultipartFile imageFile) throws Exception {
        byte[] bytes = imageFile.getBytes();

        return imageService.saveImage(bytes);
    }

//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Image image) {
//        imageRepository.delete(image);
//    }
}
