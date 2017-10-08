package util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import edu.gsu.dmlab.geometry.Point2D;
import edu.gsu.dmlab.util.CoordinateSystemConverter;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ahmetkucuk on 6/8/17.
 */
public class Utils {
    public static final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy_MM_dd_HH_mm_ss");
    public static final WKTReader reader = new WKTReader();

    public static String intervalToImageName(Interval interval) {
        DateTime start = interval.getStart();
        return fmt.print(start);
    }

    public static String generateBbox(String input) {

        try {
            Geometry geometry = reader.read(input);
            Coordinate[] coordinates = geometry.getEnvelope().getCoordinates();
            if (coordinates.length < 4) {
                return null;
            }
            Coordinate leftTop = coordinates[0];
            Coordinate rightBottom = coordinates[2];

            Point2D pixelLeftTop = CoordinateSystemConverter.convertHPCToPixXY(new Point2D(leftTop.x, leftTop.y));
            Point2D pixelrightBottom = CoordinateSystemConverter.convertHPCToPixXY(new Point2D(rightBottom.x, rightBottom.y));

            return pixelLeftTop.x + "-" + pixelLeftTop.y + "-" + pixelrightBottom.x + "-" + pixelrightBottom.y;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
