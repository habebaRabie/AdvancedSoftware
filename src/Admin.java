import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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

    public void verifySuspendDriver() {
        String sql = "Select username from driver where status = 'SUSPENDED'";
        try (Connection con = DriverManager.getConnection(url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.isBeforeFirst()){
                System.out.println("there is no drivers need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                System.out.println("Do you want to active " + username + "\n1- Yes\n2- No");
                Scanner choice = new Scanner(System.in);
                int answer = choice.nextInt();
                if (answer == 1) {
                    String sql2 = "update driver set status = 'ACTIVE' where username = ?";
                    try {
                        PreparedStatement ins = con.prepareStatement(sql2);
                        ins.setString(1, username);
                        ins.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (answer == 2) {
                } else {
                    System.out.println("wrong choice");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void verifySuspendUser() {
        String sql = "Select username from user where status = 'SUSPENDED'";
        try (Connection con = DriverManager.getConnection(url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.isBeforeFirst()){
                System.out.println("there is no users need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                System.out.println("Do you want to active " + username + "\n1- Yes\n2- No");
                Scanner choice = new Scanner(System.in);
                int answer = choice.nextInt();
                if (answer == 1) {
                    String sql2 = "update user set status = 'ACTIVE' where username = ?";
                    try {
                        PreparedStatement ins = con.prepareStatement(sql2);
                        ins.setString(1, username);
                        ins.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (answer == 2) {
                } else {
                    System.out.println("wrong choice");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        };
    }

    boolean login() {
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        userName = input.nextLine();
        password = input.nextLine();

        if (userName.equals("Admin") && password.equals("Admin")) {
            admin.setUserName(userName);
            admin.setPassword(password);
            return true;
        } else {
            System.out.println("Wrong information");
            return false;
        }
    }

    public void verify() {
        String sql = "Select username from driver where status = 'PENDING'";
        try (Connection con = DriverManager.getConnection(url)) {
            ResultSet rs1 = con.createStatement().executeQuery(sql);

            if (!rs1.next()){
                System.out.println("there is no drivers need to be verified");
            }
            while (rs1.next()) {
                String username = rs1.getString("username");
                System.out.println("Do you want to verify " + username + "\n1- Yes\n2- No");
                Scanner choice = new Scanner(System.in);
                int answer = choice.nextInt();
                if (answer == 1) {
                    String sql2 = "update driver set status = 'ACTIVE' where username = ?";
                    try {
                        PreparedStatement ins = con.prepareStatement(sql2);
                        ins.setString(1, username);
                        ins.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (answer == 2) {
                } else {
                    System.out.println("wrong choise");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        };
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

    public static ArrayList<User> getAllUsers() {
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

    //static private ArrayList<User> suspendUsers = new ArrayList<User>();
    //static private ArrayList<Driver> suspendDrivers = new ArrayList<Driver>();

    /* static public void suspend(Person person) {
         System.out.println("Please enter the userName");

         if (person instanceof User) {
             suspendUsers.add((User) person);
             removeUser((User) person);
             for (User u : users) {
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
