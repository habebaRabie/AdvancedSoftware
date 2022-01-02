
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

enum UserStatus {
    ACTIVE, SUSPENDED, INRIDE, WAITING, OFFLINE
}

public class User extends Person {

    private UserStatus status;
    private int passangerNum;
    private boolean fistRide;
    //userRequestRide rideRequest;

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

//    public userRequestRide getRideRequest() {
//        return rideRequest;
//    }

    public User() {
        this.status = UserStatus.ACTIVE;
    }

//    public User(int userID, String userName, String password, String email, String phoneNumber) {
//        super(userName, password, email, phoneNumber);
//    }

    public void register(String userName, String password, String email, String phoneNumber, String birthDate)throws Exception {
        Date date1= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
//        Scanner input = new Scanner(System.in);
//        System.out.println("Please enter your information: ");
//        System.out.println("Username: ");
//        userName = input.nextLine();
//        System.out.println("Password: ");
//        password = input.nextLine();
//        System.out.println("Email (optional): press enter to skip it if you want");
//        email = input.nextLine();
//        System.out.println("Phone Number: ");
//        phoneNumber = input.nextLine();
        
        String sql = "insert into user (username, password, email, phone, status, birthDate) values (?, ?, ?, ?, ?, ?)";
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
            ins.setDate(6, date1);
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User has been registered successfully");

    }


    static User login(String Name , String pass) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        /*System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine().trim();
        String pass = input.nextLine().trim();*/

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
                        if (u.getStatus().equals(UserStatus.SUSPENDED)){
                            System.out.println("Please wait until the verification of your account finish");
                            return null;
                        }
                        else{
                        System.out.println("Logged in successfully");
                        return u;
                        }
                    } else {
                        System.out.println("Wrong username or password");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
//TODO//////////////////////////////////
    public void rateDriver(Driver driver) {
        driver.setMyRate(this);
    }

//    public void setRideRequest(userRequestRide rideRequest) {
//        this.rideRequest = rideRequest;
//    }

//    static User loginUser() {
//        ArrayList<User> allUsers = Admin.getAllUsers();
//        Boolean found = false;
//        System.out.println("Please enter your username and password");
//        Scanner input = new Scanner(System.in);
//        String Name = input.nextLine();
//        String pass = input.nextLine();
//        for (User user : allUsers) {
//            if (user.getUserName().equals(Name)) {
//                found = true;
//                if (user.getPassword().equals(pass)) {
//                    System.out.println("Logged in successfully");
//                    user.setStatus(UserStatus.ACTIVE);
//                    return user;
//                } else {
//                    System.out.println("Wrong password");
//                    return null;
//                }
//            }
//
//        }
//        if (found){
//            System.out.println("Wrong password or user name");
//        }
//        else {
//            System.out.println("There is no user with this name or your account is suspended");
//        }
//
//        return null;
//    }

}
