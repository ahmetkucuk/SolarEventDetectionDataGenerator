package service;

import database.ImageDBConnectionManager;
import org.joda.time.Interval;
import util.ImageWriterThreadPoolExecutor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ahmetkucuk on 6/8/17.
 */
public class ImageQueryService {

    public static final int[] WAVELENGTHS = new int[]{94, 131, 171, 193, 211, 304, 335, 1600, 1700};
    public static final ImageDBConnectionManager imageDBConnectionManager = new ImageDBConnectionManager();
    ImageWriterThreadPoolExecutor executor = new ImageWriterThreadPoolExecutor();

    public boolean pullImages(Interval interval, String outputDir, String id) {

        boolean isSucceessful = true;
        try {
            for (int w :WAVELENGTHS) {
                BufferedImage image = imageDBConnectionManager.getImageDB().getFirstFullImage(interval, w);
                if(image == null) {
                    isSucceessful = false;
                    break;
                }
                File outputfile = new File(outputDir + id + "_" + w + ".jpg");
                executor.writeImage(image, outputfile);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            isSucceessful = false;
        }
        return isSucceessful;
    }
}
