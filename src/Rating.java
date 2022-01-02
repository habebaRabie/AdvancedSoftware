import java.sql.*;
import java.util.Scanner;

public class Rating {

//    HashMap<User, Integer > rating = new HashMap<>();

//    public void getRating() {
//        if (rating.size() == 0) {
//            System.out.println("No user rated you yet");
//            return;
//        }
//        for (HashMap.Entry<User, Integer> rate : rating.entrySet()) {
//            System.out.println(rate.getKey().getUserName() + " rating is : " + rate.getValue());
//        }
//    }
    
    public void printRatings(Driver driver){
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String query = "select user,rating from rating where driver = " + driver.getUserName();
        try ( Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rates = stmt.executeQuery(query);
            if (rates.next() == false){
                System.out.println("No user rated you yet");
            }
            while (rates.next()) {
                System.out.println(rates.getString("user") + " rating is : " + rates.getString("rating"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setRating(User user, Driver driver) {
        System.out.println("Please rate the selected driver");
        Scanner input = new Scanner(System.in);
        int rate = input.nextInt();
        
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String query = "insert into rating (driver, user, rating) values (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(query);
            ins.setString(1, driver.getUserName());
            ins.setString(2, user.getUserName());
            ins.setInt(3, rate);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
//        rating.put(user, rate);
    }

    public double calcAvgRate(Driver driver) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String query = "select avg(rating) from rating where driver = " + driver.getUserName();
        try ( Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet averageRate = stmt.executeQuery(query);
            return averageRate.getDouble(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
