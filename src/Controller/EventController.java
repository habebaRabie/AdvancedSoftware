package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventController {
    public double event1 (String time, String driverName, double price) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String sql = "insert into event1 (eventName, time, driverName, price) values (?, ?, ?, ?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            String eventName = "CAPTAINSETPRICE";
            ins.setString(1, eventName);
            ins.setString(2, time);
            ins.setString(3, driverName);
            ins.setDouble(4, price);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return price;
    }

    public String event2 (String time, String userName) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String sql = "insert into event2 (eventName, time, username) values (?, ?, ?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            String eventName = "USERACCEPTPRICE";
            ins.setString(1, eventName);
            ins.setString(2, time);
            ins.setString(3, userName);

            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userName;
    }

    public String event3 (String time, String userName, String eventName, String driverName) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\SW.db";
        String sql = "insert into event3 (eventName, time, driverName, username) values (?, ?, ?, ?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, eventName);
            ins.setString(2, time);
            ins.setString(4, driverName);
            ins.setString(4, userName);
            ins.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userName;
    }
}
