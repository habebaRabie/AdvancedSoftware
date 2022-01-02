import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    static Person admin = new Admin();
    public void setDiscount(String location){
        String sql2 = "update Area SET discount = 'true' WHERE location = ?";
        try( Connection con = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = con.prepareStatement(sql2);
            ins.setString(1, location);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> verifySuspendDriver() {
        ArrayList<String> allSuspendDrivers = new ArrayList<>();
        String sql = "Select username from driver where status = 'SUSPENDED'";
        try (Connection con = DriverManager.getConnection(Admin.url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.isBeforeFirst()){
                System.out.println("there is no drivers need to be verified");
                allSuspendDrivers.add("there is no drivers need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                allSuspendDrivers.add(username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allSuspendDrivers;
    }

    public void verifySpecificSuspendDriver(String username) {
        String sql1 = "select count(*) from driver where username = " + username;
        Boolean exist = false;
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql1);
            exist = RS.getBoolean(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(exist){
            String sql2 = "update driver SET status = 'ACTIVE' WHERE username = ?";
            try( Connection con = DriverManager.getConnection(Admin.url)) {
                PreparedStatement ins = con.prepareStatement(sql2);
                ins.setString(1, username);
                ins.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String suspendSpecificDriver(String name){
        String sql = "update driver set status = 'ACTIVE' where username = ?";
        try(Connection con = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = con.prepareStatement(sql);
            ins.setString(1, name);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Activated Successfully";
    }

    public ArrayList<String> verifySuspendUser() {
        ArrayList<String> allSuspendUsers = new ArrayList<>();
        String sql = "Select username from user where status = 'SUSPENDED'";
        try (Connection con = DriverManager.getConnection(Admin.url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.isBeforeFirst()){
                System.out.println("there is no users need to be verified");
                allSuspendUsers.add("there is no users need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                allSuspendUsers.add(username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allSuspendUsers;
    }

    public String suspendSpecificUser(String name){
        String sql = "update driver set status = 'ACTIVE' where username = ?";
        try(Connection con = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = con.prepareStatement(sql);
            ins.setString(1, name);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Activated Successfully";
    }

    boolean login(String userName, String password) {

        if (userName.equals("Admin") && password.equals("Admin")) {
            admin.setUserName(userName);
            admin.setPassword(password);
            return true;
        } else {
            System.out.println("Wrong information");
            return false;
        }
    }

    public ArrayList<String> verify() {
        ArrayList<String> allPendingDrivers = new ArrayList<>();
        String sql = "Select username from driver where status = 'PENDING'";
        try (Connection con = DriverManager.getConnection(Admin.url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.next()){
                System.out.println("there is no drivers need to be verified");
                allPendingDrivers.add("there is no drivers need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                allPendingDrivers.add(username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allPendingDrivers;
    }

    public String verifySpecificUser(String name){
        String sql = "update driver set status = 'ACTIVE' where username = ?";
        try(Connection con = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = con.prepareStatement(sql);
            ins.setString(1, name);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Activated Successfully";
    }

    public boolean suspendUser (String username){
        try (Connection con = DriverManager.getConnection(Admin.url)){
            String sql1 = "select count(*) FROM user WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, username);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        String sql2 = "update user set status = 'SUSPENDED' where username = ?";
                        try {
                            PreparedStatement ins = con.prepareStatement(sql2);
                            ins.setString(1, username);
                            ins.executeUpdate();
                            return true;
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else {
                        //System.out.println("Didn't found that user");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suspendDriver (String username){
        try (Connection con = DriverManager.getConnection(Admin.url)){
            String sql1 = "select count(*) FROM driver WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, username);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        String sql2 = "update driver set status = 'SUSPENDED' where username = ?";
                        try {
                            PreparedStatement ins = con.prepareStatement(sql2);
                            ins.setString(1, username);
                            ins.executeUpdate();
                            return true;
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else {
                        //System.out.println("Didn't found that user");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String setHolidays(String date) throws ParseException {
        String sql = "insert into Holiday (date) values (?)";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, date);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        System.out.println("Holiday has been added successfully");
        return "Holiday has been added successfully";
    }
}
