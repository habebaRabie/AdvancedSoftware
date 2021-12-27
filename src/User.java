
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

enum UserStatus {
    ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE
}

public class User extends Person {

    private int userID;
    private UserStatus status;
    private static int count;
    userRequestRide rideRequest;

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public UserStatus getStatus() {
        return status;
    }

    public static int getCount() {
        return count;
    }

    public userRequestRide getRideRequest() {
        return rideRequest;
    }

    public User() {
        this.userID = count;
        this.status = UserStatus.ACTIVE;
        count++;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public User(int userID, String userName, String password, String email, String phoneNumber) {
        super(userName, password, email, phoneNumber);
        this.userID = count;
        count++;
    }

    public int getUserID() {
        return userID;
    }

    public void register() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your information: ");
        System.out.println("Username: ");
        userName = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        System.out.println("Email (optional): press enter to skip it if you want");
        email = input.nextLine();
        System.out.println("Phone Number: ");
        phoneNumber = input.nextLine();
        // status = UserStatus.ACTIVE;
        Admin.addActiveUser(this);


        String sql = "insert into user (username, password, email, phone, status) values (?, ?, ?, ?, ?)";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, userName);
            ins.setString(2, password);
            ins.setString(3, email);
            ins.setString(4, phoneNumber);
            ins.setString(5, "ACTIVE");
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    static User loginUser() {
        ArrayList<User> allUsers = Admin.getAllUsers();
        Boolean found = false;
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine();
        String pass = input.nextLine();
        for (User user : allUsers) {
            if (user.getUserName().equals(Name)) {
                found = true;
                if (user.getPassword().equals(pass)) {
                    System.out.println("Logged in successfully");
                    user.setStatus(UserStatus.ACTIVE); //= UserStatus.ACTIVE;
                    return user;
                } else {
                    System.out.println("Wrong password");
                    return null;
                }
            }

        }
        if (found){
            System.out.println("Wrong password or user name");
        }
        else {
            System.out.println("There is no user with this name or your account is suspended");
        }

        return null;
    }

    static User logindb() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine().trim();
        String pass = input.nextLine().trim();

        try (Connection conn = DriverManager.getConnection(url)){
            String query = "select count(*) FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Name);
            pst.setString(2, pass);

            try (ResultSet rs = pst.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        User u = new User ();
                        while (rs.next()){
                            u.setUserName(rs.getString("username"));
                            u.setPassword(rs.getString("password"));
                            u.setPhoneNumber(rs.getString("phone"));
                            u.setPEmail(rs.getString("email"));
                            String status = rs.getString("status");
                            UserStatus s = UserStatus.valueOf(status);
                            u.setStatus(s);
                        }
                        System.out.println("Logged in successfully");
                        return u;
                    } else {
                        System.out.println("Wrong password or user name");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }


   
    public Ride requestUserRide() {
        Ride ride = new Ride();
        return ride.requestRide();
    }

    public void setRideRequest(userRequestRide rideRequest) {
        this.rideRequest = rideRequest;
    }

   
    public void rateDriver(Driver driver) {
        driver.setMyRate(this);
    }
}
