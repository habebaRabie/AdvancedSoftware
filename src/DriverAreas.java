import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverAreas {

//    ArrayList<String> favouriteArea = new ArrayList<>();

//    public void addFavAreas(String inputArea) {
//       // System.out.println("Enter the area you want to add");
////        Scanner inputArea = new Scanner(System.in);
//        favouriteArea.add(inputArea);
//    }

//    public void addFavArea(String area) {
//        favouriteArea.add(area);
//    }
//
//    public ArrayList<String> getAllArea() {
//        return favouriteArea;
//    }
//
//    public String getLastaddedArea() {
//        return favouriteArea.get(favouriteArea.size() - 1);
//    }

    public void setMyAreas(String myNewArea, String userName) {
        // myAreas.addFavAreas(myNewArea);
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String query = "insert into favoriteAreas (driver, area) values (?, ?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(query);
            ins.setString(1, userName);
            ins.setString(2, myNewArea);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String query2 = "insert into Areas (location) values (?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(query2);
            ins.setString(1, myNewArea);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
