import java.util.ArrayList;

public interface Observer {
    ArrayList<User> userObservers = new ArrayList<User>();
    ArrayList<Driver> driverObservers = new ArrayList<Driver>();

    public void update();
}
