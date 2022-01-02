package ModulesPackage;

enum EventTypes {
    CAPTAINSETPRICE, USERACCEPTPRICE, CAPTAINARRIVED, ARRIVIEDTODISTINATAION, PENDING
}


public class Event {
    private String source;
    private String info = "";
    private EventTypes type = EventTypes.PENDING;
    private String rideID;

    public void setSource(String source) {
        this.source = source;
    }

    public void setInfo(String info) {
        this.info += info;
    }

    public void setType(EventTypes type) {
        this.type = type;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    public String getSource() {
        return source;
    }

    public String getInfo() {
        return info;
    }

    public EventTypes getType() {
        return type;
    }

    public String getRideID() {
        return rideID;
    }


}
