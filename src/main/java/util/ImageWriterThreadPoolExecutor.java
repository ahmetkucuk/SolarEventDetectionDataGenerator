package util;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ahmetkucuk on 6/9/17.
 */
public class ImageWriterThreadPoolExecutor {

    public static final int THREAD_COUNT = 120;
    private static ExecutorService executorService;

    public ImageWriterThreadPoolExecutor() {
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public void writeImage(BufferedImage image, File outputFile) {
        executorService.execute(() -> {
                    try {
                        ImageIO.write(image, "jpg", outputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
