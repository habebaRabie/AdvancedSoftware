import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends Person {

    String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
    static Person admin = new Admin();

    public void setDiscount(String location){
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql2 = "update Area SET discount = 'true' WHERE location = ?";
        try( Connection con = DriverManager.getConnection(url)) {
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
        try (Connection con = DriverManager.getConnection(url)) {
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

    public String suspendSpecificDriver(String name){
        String sql = "update driver set status = 'ACTIVE' where username = ?";
        try(Connection con = DriverManager.getConnection(url)) {
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
        try (Connection con = DriverManager.getConnection(url)) {
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
        try(Connection con = DriverManager.getConnection(url)) {
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
        try (Connection con = DriverManager.getConnection(url)) {
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
        try(Connection con = DriverManager.getConnection(url)) {
            PreparedStatement ins = con.prepareStatement(sql);
            ins.setString(1, name);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Activated Successfully";
    }

    public boolean suspendUser (String username){
        try (Connection con = DriverManager.getConnection(url)){
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
        try (Connection con = DriverManager.getConnection(url)){
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

    @Override
    public String getUserName() {
        return this.userName;
    }

    public String setHolidays(String date) throws ParseException {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "insert into Holiday (date) values (?)";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(url)) {
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

   /* public Boolean searchUser(String userName) {
        for (User user : users) {
            if (user.userName.equals(userName)) {
                suspend(user);
                return true;
            }
        }
        return false;
    }

    public Boolean searchDriverExistance(String userName) {
        for (Driver driver : drivers) {
            if (driver.userName.equals(userName)) {
                suspend(driver);
                return true;
            }
        }
        return false;
    }*/

    /*public static ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    public static ArrayList<User.User> getAllUsers() {
        return users;
    }

    public static void addActiveUser(User user) {
        users.add(user);
    }

    public static void addDriverToSystem(Driver driver) {
        ALLdrivers.add(driver);
    }

    public static void removeDriverToSystem(Driver driver) {
        ALLdrivers.remove(driver);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void addActiveDriver(Driver driver) {
        if (searchDriver(driver)) {
            drivers.add(driver);
        } else {
            System.out.println("This driver doesn't exist in the system");
        }
    }

    public static void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    public static Boolean searchDriver(Driver driver) {
        if (ALLdrivers.contains(driver) ||suspendDrivers.contains(driver)) {
            return true;
        } else {
            return false;
        }
    }*/
// static private ArrayList<Driver> ALLdrivers = new ArrayList<Driver>();

    //  static private ArrayList<User> users = new ArrayList<User>();//after login users.add(active logged user)
    //static private ArrayList<Driver> drivers = new ArrayList<Driver>();

    //static private ArrayList<User> suspendUsers = new ArrayList<User.User>();
    //static private ArrayList<Driver> suspendDrivers = new ArrayList<Driver>();

    /* static public void suspend(Person person) {
         System.out.println("Please enter the userName");

         if (person instanceof User) {
             suspendUsers.add((User) person);
             removeUser((User) person);
             for (User.User u : users) {
                 if (person == u) {
                     u.setStatus(UserStatus.SUSPENDED);

                 }
             }
         } else {
             suspendDrivers.add((Driver) person);
             removeDriver((Driver) person);
             for (Driver d : drivers) {
                 if (person == d) {
                     d.setDriverStatus(DriverStatus.SUSPENDED);

                 }
             }
         }
     }*/

}
