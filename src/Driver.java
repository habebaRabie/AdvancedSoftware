import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;



enum DriverStatus {
    ACTIVE, REJECTED, SUSPENDED, PENDING, WAITING, OFFLINE, INDRIVE
}

public class Driver extends Person {

    private String nationalId;
    private String drivingLicense;
    private DriverStatus status = DriverStatus.PENDING;
//    private ArrayList<Ride> myRides = new ArrayList<>();
   private Rating myRate = new Rating();
//    private DriverAreas myAreas = new DriverAreas();


    public void setNewArea(String myNewArea) {
       // myAreas.addFavAreas(myNewArea);

    }

    public void register(String userName, String password, String email, String phoneNumber, String nationalId, String drivingLicense) {
        status = DriverStatus.SUSPENDED;

        String sql = "insert into driver (username, password, email, phone, nationalId, drivingLicense, status) values (?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DriverManager.getConnection(Admin.url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, userName);
            ins.setString(2, password);
            ins.setString(3, email);
            ins.setString(4, phoneNumber);
            ins.setString(5, nationalId);
            ins.setString(6, drivingLicense);
            ins.setString(7, "SUSPENDED");
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Driver has been registered successfully");
    }

    public Driver login(String Name, String pass) {

        try ( Connection conn = DriverManager.getConnection(Admin.url)) {
            String query = "select count(*),* FROM driver WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Name);
            pst.setString(2, pass);

            try ( ResultSet rs = pst.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
//                        Driver d = new Driver();
                        this.setUserName(rs.getString("username"));
                        this.setPassword(rs.getString("password"));
                        this.setPhoneNumber(rs.getString("phone"));
                        this.setPEmail(rs.getString("email"));
                        this.setNationalId(rs.getString("nationalId"));
                        this.setDrivingLicense(rs.getString("drivingLicense"));
                        this.setDriverStatus(DriverStatus.valueOf(rs.getString("status")));
                        if (this.getStatus().equals(DriverStatus.PENDING) || this.getStatus().equals(DriverStatus.SUSPENDED)) {
                            System.out.println("Please wait until the verification of your account finish");
                            return null;
                        } else {
                            System.out.println(this.getStatus());
                            System.out.println("Logged in successfully");
                            return this;
                        }

                    } else {
                        System.out.println("Wrong password or username");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public double getAvgRate() {
        return myRate.calcAvgRate(this);
    }

    public Driver() {
    }
    public void setDriverStatus(DriverStatus state) {
        this.status = state;
    }
    public DriverStatus getStatus() {
        return status;
    }
    public Double rideOffer(double price) {
        return price;
    }

//    public Ride setRidePrice(double price){
//        Ride ride=new Price(price);
//        return ride;
//    }


//    public void getMyRate() {
//        myRate.printRatings(this);
//    }
//
//    public void setMyRate(User user) {
//        myRate.setRating(user, this);
//    }
//
//    public void setMyRides(Ride newRide) {
//        myRides.add(newRide);
//    }
//
//    public ArrayList<String> getMyAreas() {
//        return myAreas.getAllArea();
//    }
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

}
