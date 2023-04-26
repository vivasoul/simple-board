package com.odth.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public final class ImageUtils {
    private static final int THUMBNAIL_WIDTH = 60;
    private static final int THUMBNAIL_HEIGHT = 60;

    public static BufferedImage resizeImage(File imageFile) throws IOException {

        BufferedImage originalImage = ImageIO.read(imageFile);

        return resizeImage(originalImage);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage) throws IOException {

        BufferedImage thumbIamge = Thumbnails.of(originalImage)
                .size(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)
                .outputFormat("PNG")
                .outputQuality(1)
                .asBufferedImage();

        return thumbIamge;
    }
}
