enum rideStatus {ACEPTED, REJECTED, PENDINGDRIVER, PENDINGUSER}

public class Ride {
    String source;
    String destnation;
    double price;
    rideStatus mystatus ;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestnation() {
        return destnation;
    }

    public void setDestnation(String destnation) {
        this.destnation = destnation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ride(String source, String destnation) {
        this.source = source;
        this.destnation = destnation;
    }

    public Ride() {
    }



}
