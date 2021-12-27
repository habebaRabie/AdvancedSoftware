
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

enum DriverStatus {
    ACTIVE, REJECTED, SUSPENDED, PENDING, WAITING, OFFLINE, INDRIVE
}

public class Driver extends Person {

    private int driverID;
    private String nationalId;
    private String drivingLicense;
    private DriverStatus state = DriverStatus.PENDING;
    private ArrayList<Ride> myRides = new ArrayList<>();
    private Rating myRate = new Rating();
    private DriverAreas myAreas = new DriverAreas();

    public double getAvgRate() {
        return myRate.calcAvgRate();
    }

    public ArrayList<Ride> getMyRides() {
        return myRides;
    }

    public void setMyRides(Ride newRide) {
        myRides.add(newRide);
    }

    public ArrayList<String> getMyAreas() {
        return myAreas.getAllArea();
    }

    public void setMyAreas() {
        myAreas.addFavAreas();
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
        state = DriverStatus.SUSPENDED;
        Admin.addDriverToSystem(this);

        String sql = "insert into driver (username, password, email, phone, status) values (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ins = conn.prepareStatement(sql);
            ins.setString(1, userName);
            ins.setString(2, password);
            ins.setString(3, email);
            ins.setString(4, phoneNumber);
            ins.setString(5, "SUSPENDED");
            ins.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static Driver loginDriver() {
        ArrayList<Driver> allDrivers = Admin.getAllDrivers();
        //allDrivers.add(Admin.getAllDrivers());
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        String pass = input.nextLine();
        for (Driver driver : allDrivers) {
            if (driver.getUserName().equals(name)) {
                if (driver.getPassword().equals(pass) && (driver.state.equals(DriverStatus.ACTIVE)
                        || driver.state.equals(DriverStatus.INDRIVE)
                        || driver.state.equals(DriverStatus.WAITING))) {
                    System.out.println("Logged in successfully");
                    return driver;
                } else {
                    System.out.println("Wrong password");
                    return null;
                }
            }
        }
        System.out.println("Please wait until the verification of your account finish or wrong input");
        return null;
    }

    static Driver logindb() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir")+"\\SW.db";
        System.out.println("Please enter your username and password");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine().trim();
        String pass = input.nextLine().trim();

        try (Connection conn = DriverManager.getConnection(url)){
            String query = "select count(*) FROM driver WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Name);
            pst.setString(2, pass);

            try (ResultSet rs = pst.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        Driver d = new Driver ();
                        while (rs.next()){
                            d.setUserName(rs.getString("username"));
                            d.setPassword(rs.getString("password"));
                            d.setPhoneNumber(rs.getString("phone"));
                            d.setPEmail(rs.getString("email"));
                            String status = rs.getString("status");
                            DriverStatus s = DriverStatus.valueOf(status);
                            d.setDriverStatus(s);
                        }
                        if (d.getState().equals(DriverStatus.PENDING) || d.getState().equals(DriverStatus.SUSPENDED)){
                            System.out.println("Please wait until the verification of your account finish");
                            return null;
                        }
                        else {
                            System.out.println(d.getState());
                            System.out.println("Logged in successfully");
                            return d;
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
        return  null;
    }

    Driver(String userName, String password,
            String email, String phoneNumber,
            String nationalId, String drivingLicense, int driverID) {

        super(userName, password, email, phoneNumber);
        this.nationalId = nationalId;
        this.drivingLicense = drivingLicense;
        this.driverID = driverID;
    }

    public Driver() {
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void setDriverStatus(DriverStatus state) {
        this.state = state;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public int getDriverID() {
        return driverID;
    }

    public DriverStatus getState() {
        return state;
    }

    public Double rideOffer() {
        System.out.print("Please enter your offer to this ride: ");
        Scanner in = new Scanner(System.in);
        double price = in.nextDouble();
        return price;
    }

    public void getMyRate() {
        myRate.getRating();
    }

    public void setMyRate(User user) {
        myRate.setRating(user);
    }

}
