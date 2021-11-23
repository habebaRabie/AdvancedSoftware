import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    ArrayList<Driver> allDriver = new ArrayList<Driver>();
    ArrayList<User> allUser = new ArrayList<User>();
    ArrayList<Ride> allRides = new ArrayList<Ride>();
    ArrayList<Rating> allrate = new ArrayList<Rating>();
    Map<String ,StringBuilder> Areas = new HashMap<String ,StringBuilder>();//areas , list drivers

    public ArrayList<Driver> getAllDriver() {
        return allDriver;
    }

    public void setAllDriver(Driver driver) {
        allDriver.add(driver);
    }

    public ArrayList<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(User user) {
        allUser.add(user);
    }

    public ArrayList<Ride> getAllRides() {
        return allRides;
    }

    public void setAllRides(Ride ride) {
        allRides.add(ride);
    }

    public ArrayList<Rating> getAllrate() {
        return allrate;
    }

    public void setAllrate(Rating rate) {
        allrate.add(rate);
    }

    public Map<String, StringBuilder> getAreas() {
        return Areas;
    }

    public void setAreas(String location, String driverId) {
        Areas.put(location, Areas.get(location).append(driverId));
    }
}