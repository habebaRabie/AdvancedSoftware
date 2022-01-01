
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

    public int requestUserRide(String source, String destination, int passengersNum , String username) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "insert into ride (source, destination, passengersNumber,user) values (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, source);
            ins.setString(2, destination);
            ins.setInt(3, passengersNum);
            ins.setString(4, username);
            ins.setString(5, "WAITING");
            ins.setDate(6, Date.valueOf(LocalDate.now()));
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User has been registered successfully");
        return 5;

        //Ride ride = new Ride();
        //return ride.requestRide();
    }
    public ArrayList<String> RidePrice(int RideId){
        ArrayList<String> Result = new ArrayList<>();
        String req , user =  "";
        int price , passengersNumber;
        Date date , userDate;
        boolean firstRide;
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql2 = "select passengersNumber , date , user from ride where RideID = " + RideId;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql2);
            passengersNumber = RS.getInt("passengersNumber");
            user = RS.getString("user") ;
            date = RS.getDate("date");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "select firstRide , birthDate  from user where username = " + user;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql3);
            passengersNumber = RS.getInt("passengersNumber");
            firstRide = RS.getBoolean("firstRide") ;
            userDate = RS.getDate("birthDate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "select driverName , price from RideRequest where RideID = " + RideId;
            try (Connection conn = DriverManager.getConnection(url)) {
                ResultSet RS = conn.createStatement().executeQuery(sql);
                  while (RS.next()){
                      //TODO////////////////////////////////////////
                     // if()
                      price = RS.getInt("price");
                   req = RS.getString("driverName") + " "+ String.valueOf(price);
                      Result.add(req);
                  }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
      return Result;
    }
    //TODO//////////////////////////////////
    public void SelectRidePrice(String driverName , int RideID){
        String req;
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        String sql = "select price from RideRequest where driverName = " +driverName ;
        try (Connection conn = DriverManager.getConnection(url)) {
            ResultSet RS = conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql2 = "select price from RideRequest where driverName = " +driverName ;
    }

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
