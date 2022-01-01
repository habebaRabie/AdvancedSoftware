
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverAreas {

    ArrayList<String> favouriteArea = new ArrayList<>();

    public void addFavAreas(String inputArea) {
       // System.out.println("Enter the area you want to add");
//        Scanner inputArea = new Scanner(System.in);
        favouriteArea.add(inputArea);
    }

    public void addFavArea(String area) {
        favouriteArea.add(area);
    }

    public ArrayList<String> getAllArea() {
        return favouriteArea;
    }

    public String getLastaddedArea() {
        return favouriteArea.get(favouriteArea.size() - 1);
    }
}
