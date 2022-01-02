import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublicHolidayDiscount extends Discount {
    Ride ride;

    PublicHolidayDiscount(Ride ride){
        this.ride = ride;
    }

    @Override
    public String getDescription() {
        return ride.description + " , public Holiday Discount";
    }

    @Override
    public double cost() {
        return 0.95* (ride.cost());
    }
}
