import java.util.Scanner;

enum rideStatus {ACCEPTED, REJECTED, PENDINGDRIVER, PENDINGUSER}

public class Ride {
    String source;
    String destnation;
    double price;
    rideStatus mystatus ;

    public rideStatus getMystatus() {
        return mystatus;
    }

    public void setMystatus(rideStatus mystatus) {
        this.mystatus = mystatus;
    }

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

    public Ride requestRide(){
        System.out.println("please enter your location");
        Scanner in = new Scanner(System.in);
        String src = in.next();
        System.out.println("please enter the location of destination");
        //Scanner in = new Scanner(System.in);
        String des = in.next();
        Ride myRide = new Ride(src , des);
        return myRide;
    }

    public Ride() {
    }



}
