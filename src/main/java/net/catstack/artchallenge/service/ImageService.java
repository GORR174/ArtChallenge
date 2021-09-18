package net.catstack.artchallenge.service;

import net.catstack.artchallenge.domain.Image;
import net.catstack.artchallenge.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${spring.application.images-path}")
    private String imagesPath;

    @Value("${spring.application.url}")
    private String hostUrl;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(byte[] bytes) throws IOException {
        var image = new Image();

        image.setDescription("Test");

        image = imageRepository.save(image);

        var newImageName = "img" + image.getId() + ".png";

        image.setUrl(hostUrl + "/files/images/" + newImageName);

        Files.createDirectories(Paths.get(imagesPath));
        Path path = Paths.get(imagesPath + newImageName);
        Files.write(path, bytes);

        return imageRepository.saveAndFlush(image);
    }

    public FileSystemResource getImageResource(String imageName) {
        return new FileSystemResource(Paths.get(imagesPath + imageName));
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
