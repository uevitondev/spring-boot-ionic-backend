package com.uevitondev.springweb.services;

import com.uevitondev.springweb.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

        String extFile = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
        if (!extFile.equals("png") && !extFile.equals("jpg")) {
            throw new FileException("Somente imagens PNG e JPG são permitidas");
        }

        try {
            BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
            if (extFile.equals("png")) {
                BufferedImage img = pngToJpg(image);
            }
            return image;
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo!");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extImage) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, extImage, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo!");
        }
    }

    public BufferedImage cropSquare(BufferedImage sourceImage) {
        int min = (sourceImage.getHeight() <= sourceImage.getWidth()) ? sourceImage.getHeight() : sourceImage.getWidth();
        return Scalr.crop(
                sourceImage,
                (sourceImage.getWidth() / 2) - (min / 2),
                (sourceImage.getHeight() / 2) - (min / 2),
                min,
                min);
    }

    public BufferedImage resize(BufferedImage sourceImage, int size) {
        return Scalr.resize(sourceImage, Scalr.Method.ULTRA_QUALITY, size);
    }


}
