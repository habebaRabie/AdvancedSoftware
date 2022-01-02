package Controller;

import java.sql.*;

public class DriverAreas {

    public void setMyAreas(String myNewArea, String userName) {
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
