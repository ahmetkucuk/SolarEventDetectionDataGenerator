package database;

import edu.gsu.dmlab.solgrind.database.DBConnection;
import model.Event;
import util.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetkucuk on 6/6/17.
 */
public class EventDBConnection {

    private static final EventDBConnection instance = new EventDBConnection();

    private Connection connection;

    private EventDBConnection() {

    }

    public void connect() {
        try
        {
            System.out.println("Connecting to DB");
            DriverManager.deregisterDriver(new org.postgresql.Driver());
            // the postgresql driver string

            // the postgresql url
            String url = "jdbc:postgresql://" + DBPrefs.DB_HOST + "/" + DBPrefs.DB_NAME;

            // get the postgresql database connection
            connection = DriverManager.getConnection(url,DBPrefs.DB_USERNAME, DBPrefs.DB_USER_PASSWORD);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
    }

    public void closeConnection() {
        try {
            if(this.connection != null) {
                this.connection.close();
                connection = null;
            }
        } catch (SQLException e) {}
    }

    public static EventDBConnection getNewConnection() {
        EventDBConnection connection = new EventDBConnection();
        connection.connect();
        return connection;
    }


    public Connection getConnection() {
        return connection;
    }

    public boolean executeCommand(String query) {
        try {
            return connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.out.println("[DBConnection-executeCommand] SQL error: " + e.getErrorCode());
        }
        return false;
    }

    public List<Event> getEvents(String query) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            List<Event> events = new ArrayList<>();
            while(rs.next()) {
                String id = rs.getString(1);
                String eventType = rs.getString(2);
                String startTime = rs.getString(3);
                String endTime = rs.getString(4);
                String bbox = rs.getString(5);
                events.add(new Event(id, eventType, startTime, endTime, Utils.generateBbox(bbox)));
            }
            rs.close();
            st.close();
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
