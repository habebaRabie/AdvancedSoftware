import java.text.SimpleDateFormat;
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
    public void register(String userName, String password, String email, String phoneNumber, String birthDate)throws Exception {
        Date date1= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
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


    public User login(String Name , String pass) {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";

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
                        while (rs.next()){
                            this.setUserName(rs.getString("username"));
                            this.setPassword(rs.getString("password"));
                            this.setPhoneNumber(rs.getString("phone"));
                            this.setPEmail(rs.getString("email"));
                            this.setStatus(UserStatus.valueOf(rs.getString("status")));
                        }
                        if (this.getStatus().equals(UserStatus.SUSPENDED)){
                            System.out.println("Please wait until the verification of your account finish");
                            return null;
                        }
                        else{
                        System.out.println("Logged in successfully");
                        return this;
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

//    public void rateDriver(Driver driver) {
//        driver.setMyRate(this);
//    }

//    public void setRideRequest(userRequestRide rideRequest) {
//        this.rideRequest = rideRequest;
//    }

}
