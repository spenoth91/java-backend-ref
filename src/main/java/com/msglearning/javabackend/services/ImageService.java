package com.msglearning.javabackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimeTypeParseException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageService.class.getName());

    public void store(MultipartFile file, Path path, String name) throws IOException, MimeTypeParseException {
        this.validateImageFile(file);
        Files.copy(file.getInputStream(), path.resolve(name));
    }

    private void validateImageFile(MultipartFile file) throws MimeTypeParseException {
        try {
            InputStream input = file.getInputStream();
            ImageIO.read(input).toString();
            // It's an image (only BMP, GIF, JPG and PNG are recognized).
        } catch (Exception e) {
            LOG.error(file.getOriginalFilename() + " " + " is not an image file.");
            throw new MimeTypeParseException(file.getOriginalFilename());
        }
    }

    public byte[] read(String absolutePath) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(absolutePath));
        } catch (IOException e) {
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }


}
