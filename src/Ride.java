import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

enum rideStatus {
    ACCEPTED, REJECTED, PENDINGDRIVER, PENDINGUSER
}

public abstract class Ride {
    String Source, destination;
    int passengersNumber;

    public Ride() {
        Source = "";
        this.destination = "";
        this.passengersNumber = 0;
        this.description = description;
    }

    String description="New Ride";
    public String getDescription(){
        return description;
    }
    public abstract double cost();
    public static double price;
}
