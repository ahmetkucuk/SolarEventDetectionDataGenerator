package service;

import database.EventDBConnection;
import javafx.util.Pair;
import model.Event;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetkucuk on 6/6/17.
 */
public class EventQueryService {

    private static final String QUERY_BASE = "SELECT kb_archivid, event_type, event_starttime, event_endtime, ST_asText(hpc_bbox) from %s WHERE tsrange(%s.event_starttime, %s.event_endtime) && tsrange('%s', '%s') AND frm_name = '%s';";
    private static final Pair[] EVENT_TYPES;

    static {
        EVENT_TYPES = new Pair[]{
                new Pair<>("ar", "SPoCA"),
                new Pair<>("ch", "SPoCA"),
                new Pair<>("fl", "SWPC"),
                new Pair<>("fi", "AAFDCC"),
                new Pair<>("sg", "Sigmoid Sniffer"),
                new Pair<>("ss", "EGSO_SFC")};
    }

    public List<Event> getEventsByInterval(Interval interval) {
        List<String> queries = buildEventQueryStrings(interval);
        System.out.println(queries.get(0));
        EventDBConnection connection = EventDBConnection.getNewConnection();
        List<Event> allEvents = new ArrayList<>();
        for(String query: queries) {
            allEvents.addAll(connection.getEvents(query));
        }
        connection.closeConnection();

        return allEvents;
    }

    private List<String> buildEventQueryStrings(Interval interval) {
        List<String> list = new ArrayList<>();
        for (Pair<String, String> pair: EVENT_TYPES) {
            String eventType = pair.getKey();
            String reporter = pair.getValue();
            list.add(String.format(QUERY_BASE, eventType, eventType, eventType, interval.getStart().toString(), interval.getEnd().toString(), reporter));
        }

        return list;
    }
}
