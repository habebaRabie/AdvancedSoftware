
import java.util.ArrayList;

public class DriverAreas {
    ArrayList<String> favouriteArea;

    public void addFavAreas(String location) {
        favouriteArea.add(location);
    }

    public ArrayList<String> getAllArea() {
        return favouriteArea;
    }
}
