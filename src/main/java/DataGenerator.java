import database.EventDBConnection;
import database.ImageDBConnectionManager;
import edu.gsu.dmlab.solgrind.database.DBConnection;
import model.Event;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import service.EventQueryService;
import service.ImageQueryService;
import util.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmetkucuk on 6/6/17.
 */
public class DataGenerator {

    public static void main(String[] args) throws IOException, SQLException {

        startDatasetCreation(args[0], args[1]);
    }

    public static void startDatasetCreation(String outputFileDirectory, String year) throws IOException {


        List<Interval> intervals = generateIntervals(Integer.parseInt(year));

        ImageQueryService imageQueryService = new ImageQueryService();
        EventQueryService eventQueryService = new EventQueryService();
        FileWriter writer = new FileWriter(outputFileDirectory + "event_records_" + year + ".txt");
        Map<String, Event> eventMap = new HashMap<>();
        for(Interval i: intervals) {
            String imageName = Utils.intervalToImageName(i);
            boolean results = imageQueryService.pullImages(i, outputFileDirectory, Utils.intervalToImageName(i));
            if (results) {
                List<Event> events = eventQueryService.getEventsByInterval(i);
                for(Event e: events) {
                    e.setImage(imageName);
                    eventMap.put(e.getId(), e);
                }
            } else {
                System.out.println("Failed for: " + i);
            }
        }

        for(Event e: eventMap.values()) {
            writer.write(e.toString() + "\n");
        }
        writer.close();
    }

    public static List<Interval> generateIntervals(int startYear) {

        List<Interval> intervals = new ArrayList<>();
        DateTime start = new DateTime(startYear, 1, 1, 0, 0, 0, 0);

        DateTime end = new DateTime(start).minusMinutes(-15);
        while(new DateTime(startYear+1, 1, 1, 0, 0, 0, 0).getMillis() > end.getMillis()) {
            Interval interval = new Interval(start, end);
            intervals.add(interval);
            start = start.minusHours(-1);
            end = end.minusHours(-1);
        }

        return intervals;
    }


}
