package Controller;

import ModulesPackage.*;
import Controller.*;
import ModulesPackage.Driver;

import java.sql.*;

public class Rating {
    
    public void printRatings(ModulesPackage.Driver driver){
        String query = "select user,rating from rating where driver = " + driver.getUserName();
        try ( Connection conn = DriverManager.getConnection(Admin.url)) {
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

    public void setRating(User user, Driver driver, int rate) {
        String query = "insert into rating (driver, user, rating) values (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = conn.prepareStatement(query);
            ins.setString(1, driver.getUserName());
            ins.setString(2, user.getUserName());
            ins.setInt(3, rate);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double calcAvgRate(ModulesPackage.Driver driver) {
        String query = "select avg(rating) from rating where driver = " + driver.getUserName();
        try ( Connection conn = DriverManager.getConnection(Admin.url)) {
            Statement stmt = conn.createStatement();
            ResultSet averageRate = stmt.executeQuery(query);
            return averageRate.getDouble(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
