package model;

/**
 * Created by ahmetkucuk on 6/7/17.
 */
public class Event {

    private String id;
    private String eventType;
    private String startTime;
    private String endTime;
    private String bbox;
    private String image;

    public Event(String id, String eventType, String startTime, String endTime, String bbox) {
        this.id = id;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bbox = bbox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBbox() {
        return bbox;
    }

    public void setBbox(String bbox) {
        this.bbox = bbox;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return id + "\t" + eventType + "\t" + startTime + "\t" + endTime + "\t" + bbox  + "\t" + image;
    }
}
